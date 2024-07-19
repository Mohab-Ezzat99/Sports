package com.app.sports.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.app.sports.BuildConfig
import com.app.sports.data.local.AppDB
import com.app.sports.data.remote.RetrofitApi
import com.app.sports.data.reporsitory.MainRepositoryImpl
import com.app.sports.domain.repository.MainRepository
import com.app.sports.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): RetrofitApi =
        Retrofit.Builder()
            .baseUrl(RetrofitApi.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor { chain: Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("X-Auth-Token", BuildConfig.FOOTBALL_TOKEN)
                    .build()
                chain.proceed(newRequest)
            }.build()

    @Provides
    @Singleton
    fun provideAppDB(app: Application): AppDB {
        return Room.databaseBuilder(app, AppDB::class.java, "AppDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDao(appDB: AppDB) = appDB.dao

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext app: Application) =
        app.getSharedPreferences(Constants.PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideMainRepository(retrofitApi: RetrofitApi, db: AppDB): MainRepository {
        return MainRepositoryImpl(retrofitApi, db.dao)
    }
}

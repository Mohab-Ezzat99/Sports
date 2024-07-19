package com.app.sports.data.remote

import com.app.sports.data.remote.dto.AllLeaguesDto
import com.app.sports.data.remote.dto.LeagueDetailsDto
import com.app.sports.data.remote.dto.LeagueTeamsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {

    companion object {
        const val BASE_URL = "https://api.football-data.org/v4/"
    }

    @GET("competitions")
    suspend fun getAllLeagues(): Response<AllLeaguesDto>

    @GET("competitions/{id}")
    suspend fun getLeagueDetailsById(@Path("id") id: Int): Response<LeagueDetailsDto>

    @GET("competitions/{id}/teams")
    suspend fun getLeagueTeamsById(@Path("id") id: Int): Response<LeagueTeamsDto>
}

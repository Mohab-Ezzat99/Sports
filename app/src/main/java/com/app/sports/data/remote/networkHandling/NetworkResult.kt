package com.app.sports.data.remote.networkHandling

import retrofit2.Response

object NetworkResult {

    private lateinit var status: NetworkStatus

    fun observeNetworkStatus(status: NetworkStatus) {
        this.status = status
    }

    suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) response.body()?.let { return Resource.success(it) }
            else status.onServerSideError(response.errorBody()?.source()?.buffer?.readUtf8())
        } catch (e: Exception) {
            e.printStackTrace()
            status.onConnectionFail(e.message.toString())
        }
        return Resource.error("")
    }
}

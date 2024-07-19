package com.app.sports.data.remote.networkHandling

interface NetworkStatus {
    fun onServerSideError(exception: String?)
    fun onConnectionFail(exception: String?)
}

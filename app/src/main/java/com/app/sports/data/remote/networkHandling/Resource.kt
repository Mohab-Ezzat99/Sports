package com.app.sports.data.remote.networkHandling

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status { SUCCESS, ERROR }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }
    }
}

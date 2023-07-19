package com.example.e_comm_home_demo_app.common

sealed class ReponseHandler<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): ReponseHandler<T>(data)
    class Error<T>(message: String, data: T? = null): ReponseHandler<T>(data, message)
    class Loading<T> : ReponseHandler<T>()
}
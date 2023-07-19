package com.example.e_comm_home_demo_app.common

import com.example.e_comm_home_demo_app.common.Constants.ApiAuthType
import com.example.e_comm_home_demo_app.common.Constants.AuthKey
import com.example.e_comm_home_demo_app.common.Constants.Cookie
import com.example.e_comm_home_demo_app.common.Constants.Token
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthIntercept @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().headers(getJsonHeader()).build()
        val response: Response?
        response = chain.proceed(request)
        return response
    }

    private fun getJsonHeader(): Headers {
        val builder = Headers.Builder()
        builder.add("apiAuthType", ApiAuthType)
        builder.add("authKey", AuthKey)
        builder.add("token", Token)
        builder.add("cookie", Cookie)
        return builder.build()
    }
}
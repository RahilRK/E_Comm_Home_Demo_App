package com.example.e_comm_home_demo_app.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.e_comm_home_demo_app.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckInternetHelper @Inject constructor(@ApplicationContext private val context: Context) {

    val networkErrorMsg = context.resources.getString(R.string.no_internet_msg)

    fun isInternetAvailable(): Boolean {
        var getResult = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        getResult = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return getResult
    }
}
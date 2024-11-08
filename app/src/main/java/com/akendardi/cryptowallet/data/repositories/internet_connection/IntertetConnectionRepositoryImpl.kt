package com.akendardi.cryptowallet.data.repositories.internet_connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.akendardi.cryptowallet.domain.repository.InternetConnectionRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InternetConnectionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : InternetConnectionRepository {

    override fun checkInternetConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
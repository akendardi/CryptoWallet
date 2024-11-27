package com.akendardi.cryptowallet.domain.repository

interface InternetConnectionRepository {

    fun checkInternetConnection(): Boolean
}
package com.akendardi.cryptowallet

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.migration.CustomInject
import dagger.hilt.android.migration.CustomInjection

@HiltAndroidApp
@CustomInject
class CryptoApp : Application(){

    override fun onCreate() {
        super.onCreate()
        CustomInjection.inject(this)
    }
}
package com.akendardi.cryptowallet

import android.app.Application
import com.akendardi.cryptowallet.di.AppComponent
import com.akendardi.cryptowallet.di.DaggerAppComponent

class CryptoApp: Application() {

    lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent.create()
    }
}
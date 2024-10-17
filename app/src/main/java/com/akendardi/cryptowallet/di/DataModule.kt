package com.akendardi.cryptowallet.di

import android.app.Application
import android.content.Context
import com.akendardi.cryptowallet.CryptoApp
import com.akendardi.cryptowallet.data.internet.api.ApiFactory
import com.akendardi.cryptowallet.data.internet.api.ApiService
import com.akendardi.cryptowallet.settings.SettingsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    companion object {
        @Provides
        fun provideApiFactory(): ApiFactory {
            return ApiFactory
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        fun provideApplicationContext(app: CryptoApp): Context {
            return app.applicationContext
        }

        @Provides
        fun provideContext(
            @ApplicationContext context: Context,
        ): Context {
            return context
        }

    }
}
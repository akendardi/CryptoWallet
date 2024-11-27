package com.akendardi.cryptowallet.di

import android.content.Context
import com.akendardi.cryptowallet.CryptoApp
import com.akendardi.cryptowallet.data.internet.api.CoinsApiFactory
import com.akendardi.cryptowallet.data.internet.api.DataCoinsApiService
import com.akendardi.cryptowallet.data.internet.api.SearchCoinsApiFactory
import com.akendardi.cryptowallet.data.internet.api.AssetsCoinsApiService
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
        fun provideCoinsApiFactory(): CoinsApiFactory {
            return CoinsApiFactory
        }

        @Provides
        fun provideCoinsApiService(): DataCoinsApiService {
            return CoinsApiFactory.dataCoinsApiService
        }

        @Provides
        fun provideSearchCoinsApiFactory(): SearchCoinsApiFactory {
            return SearchCoinsApiFactory
        }

        @Provides
        fun provideSearchCoinsApiService(): AssetsCoinsApiService {
            return SearchCoinsApiFactory.assetsCoinsApiService
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
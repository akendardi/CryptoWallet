package com.akendardi.cryptowallet.di

import com.akendardi.cryptowallet.data.internet.api.ApiFactory
import com.akendardi.cryptowallet.data.internet.api.ApiService
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    companion object{
        @Provides
        fun provideApiFactory(): ApiFactory{
            return ApiFactory
        }

        @Provides
        fun provideApiService(): ApiService{
            return ApiFactory.apiService
        }
    }
}
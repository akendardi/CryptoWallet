package com.akendardi.cryptowallet.di

import com.akendardi.cryptowallet.data.repositories.coin_operations.CoinOperationsRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.auth.AuthRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.crypto.CryptoDetailInfoRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.crypto.CryptoRepositoryGeneralInfoImpl
import com.akendardi.cryptowallet.data.repositories.internet_connection.InternetConnectionRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.user.UserInfoRepositoryImpl
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.repository.CoinOperationsRepository
import com.akendardi.cryptowallet.domain.repository.CryptoDetailInfoRepository
import com.akendardi.cryptowallet.domain.repository.CryptoRepositoryGeneralInfo
import com.akendardi.cryptowallet.domain.repository.InternetConnectionRepository
import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindCryptoRepository(impl: CryptoRepositoryGeneralInfoImpl): CryptoRepositoryGeneralInfo


    @Singleton
    @Binds
    fun bindFirebaseRepository(impl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    fun bindUserImageRepository(impl: UserInfoRepositoryImpl): UserInfoRepository

    @Singleton
    @Binds
    fun bindCoinOperationrepository(impl: CoinOperationsRepositoryImpl): CoinOperationsRepository

    @Singleton
    @Binds
    fun bindUserInternetConntectionRepository(impl: InternetConnectionRepositoryImpl): InternetConnectionRepository

    @Binds
    fun bindCryptoDetailInfoRepository(impl: CryptoDetailInfoRepositoryImpl): CryptoDetailInfoRepository
}
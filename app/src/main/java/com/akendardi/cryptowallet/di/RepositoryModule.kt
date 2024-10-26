package com.akendardi.cryptowallet.di

import com.akendardi.cryptowallet.data.repositories.auth.AuthRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.crypto.CryptoRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.user.UserInfoRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.user.UserRepositoryImpl
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.repository.CryptoRepository
import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import com.akendardi.cryptowallet.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCryptoRepository(impl: CryptoRepositoryImpl): CryptoRepository

    @Singleton
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    fun bindFirebaseRepository(impl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    fun bindUserImageRepository(impl: UserInfoRepositoryImpl): UserInfoRepository
}
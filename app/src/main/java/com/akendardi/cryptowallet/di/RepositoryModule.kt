package com.akendardi.cryptowallet.di

import com.akendardi.cryptowallet.data.repositories.auth.AuthRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.balance.BalanceOperationRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.coin_operations.OperationsCoinRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.crypto.CryptoDetailInfoRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.crypto.CryptoRepositoryGeneralInfoImpl
import com.akendardi.cryptowallet.data.repositories.internet_connection.InternetConnectionRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.transactions.CoinsTransactionsRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.transactions.UsersTransactionsRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.user.UserInfoRepositoryImpl
import com.akendardi.cryptowallet.data.repositories.user.UsersBalanceRepositoryImpl
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.repository.BalanceOperationRepository
import com.akendardi.cryptowallet.domain.repository.CoinsTransactionsRepository
import com.akendardi.cryptowallet.domain.repository.CryptoDetailInfoRepository
import com.akendardi.cryptowallet.domain.repository.CryptoRepositoryGeneralInfo
import com.akendardi.cryptowallet.domain.repository.InternetConnectionRepository
import com.akendardi.cryptowallet.domain.repository.OperationsCoinRepository
import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import com.akendardi.cryptowallet.domain.repository.UsersBalanceRepository
import com.akendardi.cryptowallet.domain.repository.UsersTransactionsRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindCryptoRepository(impl: CryptoRepositoryGeneralInfoImpl): CryptoRepositoryGeneralInfo

    @Reusable
    @Binds
    fun bindFirebaseRepository(impl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    fun bindUserImageRepository(impl: UserInfoRepositoryImpl): UserInfoRepository

    @Singleton
    @Binds
    fun bindCoinOperationrepository(impl: OperationsCoinRepositoryImpl): OperationsCoinRepository

    @Singleton
    @Binds
    fun bindUserInternetConntectionRepository(impl: InternetConnectionRepositoryImpl): InternetConnectionRepository

    @Singleton
    @Binds
    fun bindUsersTransactionsRepository(impl: UsersTransactionsRepositoryImpl): UsersTransactionsRepository

    @Singleton
    @Binds
    fun bindCoinsTransactionsRepository(impl: CoinsTransactionsRepositoryImpl): CoinsTransactionsRepository

    @Singleton
    @Binds
    fun bindUsersCoinsRepository(impl: UsersBalanceRepositoryImpl): UsersBalanceRepository

    @Binds
    fun bindBalanceOperationsRepository(impl: BalanceOperationRepositoryImpl): BalanceOperationRepository


    @Binds
    fun bindCryptoDetailInfoRepository(impl: CryptoDetailInfoRepositoryImpl): CryptoDetailInfoRepository

}

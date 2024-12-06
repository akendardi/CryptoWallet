package com.akendardi.cryptowallet.data.repositories.user

import com.akendardi.cryptowallet.data.internet.api.AssetsCoinsApiService
import com.akendardi.cryptowallet.data.internet.dto.user.BalanceInfoDto
import com.akendardi.cryptowallet.domain.repository.UsersBalanceRepository
import com.akendardi.cryptowallet.domain.states.user_info.UsersBalanceResult
import com.akendardi.cryptowallet.mapper.balanceDtoToEntity
import com.akendardi.cryptowallet.mapper.purchasedCoinDtoToEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersBalanceRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val assetsCoinsApiService: AssetsCoinsApiService
) : UsersBalanceRepository {

    private val _usersBalanceResult = MutableStateFlow<UsersBalanceResult>(UsersBalanceResult.Initial)
    override val usersBalanceResult: StateFlow<UsersBalanceResult> = _usersBalanceResult.asStateFlow()

    override suspend fun loadUsersBalance() {
        try {
            _usersBalanceResult.emit(UsersBalanceResult.Loading)
            val currentUser = auth.currentUser?.uid ?: ""
            val balance = database.reference
                .child("users")
                .child(currentUser)
                .child("balance")
                .get()
                .await()
                .getValue(BalanceInfoDto::class.java) ?: BalanceInfoDto()

            val purchasedCoins = balance.purchasedCoins.map {
                val coinDto =
                    assetsCoinsApiService.loadDetailCoinInfo(it.symbol).data.values.first()
                purchasedCoinDtoToEntity(
                    purchasedCoinDto = it,
                    currentPrice = coinDto.price
                )
            }

            val newBalance = balanceDtoToEntity(balance, purchasedCoins)

            _usersBalanceResult.emit(UsersBalanceResult.Success(newBalance))


        } catch (e: Exception) {
            _usersBalanceResult.emit(UsersBalanceResult.Error)
        }
    }
}
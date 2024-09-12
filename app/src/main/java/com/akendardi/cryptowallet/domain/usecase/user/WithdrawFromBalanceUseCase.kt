package com.akendardi.cryptowallet.domain.usecase.user

import com.akendardi.cryptowallet.domain.repository.UserRepository
import javax.inject.Inject

class WithdrawFromBalanceUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Int, amount: Double) =
        userRepository.withdrawFromBalance(
            userId = userId,
            amount = amount
        )
}
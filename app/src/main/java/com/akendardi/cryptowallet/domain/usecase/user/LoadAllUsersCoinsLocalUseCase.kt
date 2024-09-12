package com.akendardi.cryptowallet.domain.usecase.user

import com.akendardi.cryptowallet.domain.repository.UserRepository
import javax.inject.Inject

class LoadAllUsersCoinsLocalUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.loadUsersCoinsListLocal()
}
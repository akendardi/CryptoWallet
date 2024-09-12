package com.akendardi.cryptowallet.domain.usecase.user

import com.akendardi.cryptowallet.domain.repository.UserRepository
import javax.inject.Inject

class LoadAllUsersCoinsRemoteUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Int) = userRepository.loadUsersCoinsListRemote(userId)
}
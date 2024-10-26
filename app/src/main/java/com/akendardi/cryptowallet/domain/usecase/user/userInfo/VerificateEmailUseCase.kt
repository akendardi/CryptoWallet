package com.akendardi.cryptowallet.domain.usecase.user.userInfo

import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import javax.inject.Inject

class VerificateEmailUseCase @Inject constructor(private val repository: UserInfoRepository) {
    suspend operator fun invoke() = repository.verificateEmail()
}
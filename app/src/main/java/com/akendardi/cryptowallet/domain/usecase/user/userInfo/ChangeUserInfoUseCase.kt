package com.akendardi.cryptowallet.domain.usecase.user.userInfo

import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import javax.inject.Inject

class ChangeUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    suspend fun changeName(name: String) = userInfoRepository.changeUserName(name)

    suspend fun changeEmail(email: String, password: String) =
        userInfoRepository.changeEmail(email, password)

    suspend fun changePassword(oldPassword: String, newPassword: String) =
        userInfoRepository.changePassword(oldPassword, newPassword)
}
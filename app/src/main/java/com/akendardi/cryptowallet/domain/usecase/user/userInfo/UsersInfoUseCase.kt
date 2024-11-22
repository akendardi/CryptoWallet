package com.akendardi.cryptowallet.domain.usecase.user.userInfo

import com.akendardi.cryptowallet.domain.entity.user_info.UserInfoGeneral
import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import com.akendardi.cryptowallet.domain.states.user_info.UserInfoGeneralInfoResult
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    fun observeUserInfo(): StateFlow<UserInfoGeneral> =
        userInfoRepository.userInfoFlow

    fun observeRequestAnswers(): SharedFlow<UserProfileOperationResult> =
        userInfoRepository.requestAnswer

    suspend fun resetRequestAnswer() = userInfoRepository.resetRequestAnswer()

    suspend operator fun invoke() {
        userInfoRepository.loadProfileInfo()
    }
}
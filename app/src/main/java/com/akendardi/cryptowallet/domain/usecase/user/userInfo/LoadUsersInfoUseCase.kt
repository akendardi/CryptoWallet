package com.akendardi.cryptowallet.domain.usecase.user.userInfo

import com.akendardi.cryptowallet.domain.entity.UserInfo
import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadUsersInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    fun observeUserInfo(): StateFlow<UserInfo> = userInfoRepository.userInfoFlow

    fun observeRequestAnswers(): SharedFlow<UserProfileOperationResult> = userInfoRepository.requestAnswer

    suspend fun resetRequestAnswer() = userInfoRepository.resetRequestAnswer()

    suspend operator fun invoke() {
        userInfoRepository.loadProfileInfo()
    }
}
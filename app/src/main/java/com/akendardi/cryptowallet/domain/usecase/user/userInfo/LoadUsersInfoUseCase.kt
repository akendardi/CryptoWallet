package com.akendardi.cryptowallet.domain.usecase.user.userInfo

import android.util.Log
import com.akendardi.cryptowallet.domain.entity.UserInfo
import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadUsersInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    fun observeUserInfo(): StateFlow<UserInfo> = userInfoRepository.userInfoFlow

    suspend operator fun invoke() {
        userInfoRepository.loadProfileInfo()
        Log.d("LoadUsersInfoUseCase", "Data loading triggered")
    }
}
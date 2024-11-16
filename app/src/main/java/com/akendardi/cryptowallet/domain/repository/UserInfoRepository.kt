package com.akendardi.cryptowallet.domain.repository

import android.net.Uri
import com.akendardi.cryptowallet.domain.entity.user_info.UserInfo
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface UserInfoRepository {

    val userInfoFlow: StateFlow<UserInfo>

    val requestAnswer: SharedFlow<UserProfileOperationResult>


    suspend fun uploadProfileImage(uri: Uri)

    suspend fun loadProfileInfo()

    suspend fun changeUserName(name: String)

    suspend fun changeEmail(email: String, currentPassword: String)

    suspend fun changePassword(currentPassword: String, newPassword: String)

    suspend fun verificateEmail()

    suspend fun resetRequestAnswer()
}

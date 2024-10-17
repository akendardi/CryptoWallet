package com.akendardi.cryptowallet.domain.repository

import android.net.Uri
import com.akendardi.cryptowallet.domain.entity.UserInfo
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import kotlinx.coroutines.flow.StateFlow

interface UserInfoRepository {

    val userInfoFlow: StateFlow<UserInfo>

    suspend fun uploadProfileImage(uri: Uri): UserProfileOperationResult

    suspend fun loadProfileInfo()

    suspend fun changeUserName(name: String): UserProfileOperationResult

    suspend fun changeEmail(email: String, currentPassword: String): UserProfileOperationResult

    suspend fun changePassword(password: String): UserProfileOperationResult
}
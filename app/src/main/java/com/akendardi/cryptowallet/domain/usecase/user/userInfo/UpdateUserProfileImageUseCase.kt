package com.akendardi.cryptowallet.domain.usecase.user.userInfo

import android.net.Uri
import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import javax.inject.Inject

class UpdateUserProfileImageUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    suspend operator fun invoke(uri: Uri) = userInfoRepository.uploadProfileImage(uri)
}
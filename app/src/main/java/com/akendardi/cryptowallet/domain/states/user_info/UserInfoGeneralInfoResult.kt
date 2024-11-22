package com.akendardi.cryptowallet.domain.states.user_info

import com.akendardi.cryptowallet.domain.entity.user_info.UserInfoGeneral

sealed class UserInfoGeneralInfoResult {
    data object Initial : UserInfoGeneralInfoResult()
    data object Loading : UserInfoGeneralInfoResult()
    data class Success(val userInfo: UserInfoGeneral) : UserInfoGeneralInfoResult()
    data class Error(val message: String) : UserInfoGeneralInfoResult()
}
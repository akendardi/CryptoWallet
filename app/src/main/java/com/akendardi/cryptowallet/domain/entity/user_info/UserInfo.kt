package com.akendardi.cryptowallet.domain.entity.user_info

import android.net.Uri

data class UserInfo(
    val userName: String = "",
    val profileUri: Uri = Uri.parse(""),
    val email: String = "",
    val isVerificationAccount: Boolean = false
)

package com.akendardi.cryptowallet.domain.entity.user_info

import android.net.Uri

data class UserInfoGeneral(
    val userName: String,
    val profileUri: Uri,
    val email: String,
    val isVerificatedAccount: Boolean
)




package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.verification_email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.VerificateEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileAlertVerificationEmailViewModel @Inject constructor(
    private val verificateEmailUseCase: VerificateEmailUseCase
) : ViewModel() {

    fun verificateEmail() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                verificateEmailUseCase()
            }
        }
    }

}
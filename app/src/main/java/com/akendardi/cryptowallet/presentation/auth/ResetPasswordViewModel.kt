package com.akendardi.cryptowallet.presentation.auth

import androidx.lifecycle.ViewModel
import com.akendardi.cryptowallet.domain.usecase.auth.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {


}
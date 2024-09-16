package com.akendardi.cryptowallet.presentation.auth

import androidx.lifecycle.ViewModel
import com.akendardi.cryptowallet.domain.usecase.auth.CreateAccountUseCase
import com.akendardi.cryptowallet.domain.usecase.auth.LogInAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase,
    private val logInAccountUseCase: LogInAccountUseCase
) : ViewModel() {


}
package com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.password

import androidx.lifecycle.ViewModel
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.UsersInfoUseCase
import com.akendardi.cryptowallet.domain.usecase.user.userInfo.change_info.ChangeUserInfoUseCase
import com.akendardi.cryptowallet.domain.usecase.validators.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileAlertPasswordViewModel @Inject constructor(
    private val usersInfoUseCase: UsersInfoUseCase,
    private val changeUserInfoUseCase: ChangeUserInfoUseCase,
    private val passwordValidator: PasswordValidator
) : ViewModel() {
    private var isFirstAttempt = true

    private val _state = MutableStateFlow(ProfileAlertPasswordState())
    val state = _state.asStateFlow()


}
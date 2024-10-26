package com.akendardi.cryptowallet.presentation.main.profile.handles

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.errors.ProfileAuthErrorAlertDialog
import com.akendardi.cryptowallet.presentation.main.profile.alert_dialog_screen.errors.ProfileInternetErrorAlertDialog
import com.akendardi.cryptowallet.presentation.main.profile.utils.LoadingProgressBar
import com.akendardi.cryptowallet.presentation.main.profile.utils.ShowSnackbarMessage

@Composable
fun HandleProfileResult(
    snackbarHostState: SnackbarHostState,
    requestAnswer: UserProfileOperationResult,
    goToLogInScreen: () -> Unit,
    closeAnswerScreen: () -> Unit

) {
    when (requestAnswer) {
        UserProfileOperationResult.AuthError -> {
            ProfileAuthErrorAlertDialog(
                onDismiss = {
                    closeAnswerScreen()
                    goToLogInScreen()
                }
            )
        }

        UserProfileOperationResult.Error -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = "Произошла ошибка. Проверьте данные и повторите попытку"
            )
        }

        UserProfileOperationResult.Initial -> {

        }

        UserProfileOperationResult.InternetError -> {
            ProfileInternetErrorAlertDialog(
                onDismiss = closeAnswerScreen
            )
        }

        UserProfileOperationResult.Loading -> {
            LoadingProgressBar()
        }

        UserProfileOperationResult.SuccessChangeEmail -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = "На вашу почту отправлено письмо"
            )
        }

        UserProfileOperationResult.SuccessChangeName -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = "Ваше имя успешно изменено"
            )
        }

        UserProfileOperationResult.SuccessChangePassword -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = "Ваш пароль успешно изменен"
            )
        }

        UserProfileOperationResult.SuccessChangeProfilePhoto -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = "Ваше фото успешно изменено"
            )
        }

        UserProfileOperationResult.SuccessVerificationEmail -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = "На вашу почту отправлена ссылка для подтверждения"
            )
        }
    }
}
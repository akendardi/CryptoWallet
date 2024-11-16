package com.akendardi.cryptowallet.presentation.profile.handles

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.errors.ProfileAuthErrorAlertDialog
import com.akendardi.cryptowallet.presentation.profile.alert_dialog_screen.errors.ProfileInternetErrorAlertDialog
import com.akendardi.cryptowallet.presentation.profile.utils.LoadingProgressBar
import com.akendardi.cryptowallet.presentation.profile.utils.ShowSnackbarMessage

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
                message = stringResource(R.string.error_profile)
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
                message = stringResource(R.string.sent_link_on_email)
            )
        }

        UserProfileOperationResult.SuccessChangeName -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = stringResource(R.string.username_changed)
            )
        }

        UserProfileOperationResult.SuccessChangePassword -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = stringResource(R.string.password_changed)
            )
        }

        UserProfileOperationResult.SuccessChangeProfilePhoto -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = stringResource(R.string.profile_photo_changed)
            )
        }

        UserProfileOperationResult.SuccessVerificationEmail -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = stringResource(R.string.sent_link_on_email)
            )
        }
    }
}
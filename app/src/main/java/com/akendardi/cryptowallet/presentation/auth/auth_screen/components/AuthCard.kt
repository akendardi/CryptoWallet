package com.akendardi.cryptowallet.presentation.auth.auth_screen.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.auth.auth_screen.AuthType
import com.akendardi.cryptowallet.presentation.auth.auth_screen.FieldType
import com.akendardi.cryptowallet.presentation.auth.auth_screen.TextFieldsErrorsState
import com.akendardi.cryptowallet.presentation.auth.auth_screen.TextFieldsState

@Composable
fun AuthCard(
    authType: AuthType,
    textFieldsState: TextFieldsState,
    textFieldsErrorsState: TextFieldsErrorsState,
    onAuthTypeChanged: (AuthType) -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit
) {

    var isAnimated by remember { mutableStateOf(false) }

    val offsetY by animateDpAsState(
        targetValue = if (isAnimated) 0.dp else 600.dp,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(Unit) {
        isAnimated = true
    }

    ElevatedCard(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
            .offset {
                IntOffset(0, offsetY.roundToPx())
            }
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardFirstText(authType)

            AnimatedNickNameTextField(
                authType = authType,
                value = textFieldsState.userName,
                error = textFieldsErrorsState.userNameError,
                onValueChanged = { onNameChanged(it) },
                onTextFieldIconClick = onTextFieldIconClick
            )

            AuthTextField(
                fieldType = FieldType.EMAIL,
                value = textFieldsState.email,
                onValueChanged = {
                    onEmailChanged(it)
                },
                error = textFieldsErrorsState.emailError,
                onTextFieldIconClick = onTextFieldIconClick
            )
            AnimatedPasswordTextField(
                authType = authType,
                value = textFieldsState.password,
                error = textFieldsErrorsState.passwordError,
                isPasswordVisible = textFieldsState.isPasswordVisible,
                onValueChanged = { onPasswordChanged(it) },
                onTextFieldIconClick = onTextFieldIconClick
            )

            AuthButton(
                authType = authType,
                onButtonClick = {
                    onButtonClick()
                })
            ChangeAuthAndReset(
                authType = authType,
                onAuthTypeChanged = {
                    onAuthTypeChanged(it)
                }
            )

        }
    }
}

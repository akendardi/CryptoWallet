package com.akendardi.cryptowallet.presentation.auth.auth_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.auth.auth_screen.components.AuthCard
import com.akendardi.cryptowallet.presentation.auth.auth_screen.components.Title

@Composable
fun AuthScreenContent(
    authType: AuthType,
    textFieldsState: TextFieldsState,
    textFieldsErrorsState: TextFieldsErrorsState,
    onAuthTypeChanged: (AuthType) -> Unit,
    onNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(20.dp))
            Title()
            AuthCard(
                authType,
                textFieldsState = textFieldsState,
                textFieldsErrorsState = textFieldsErrorsState,
                onAuthTypeChanged = {
                    onAuthTypeChanged(it)
                },
                onNameChanged = {
                    onNameChanged(it)
                },
                onEmailChanged = {
                    onEmailChanged(it)
                },
                onPasswordChanged = {
                    onPasswordChanged(it)
                },
                onButtonClick = {
                    onButtonClick()
                },
                onTextFieldIconClick = onTextFieldIconClick
            )
        }

    }


}
package com.akendardi.cryptowallet.presentation.auth.auth_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import com.akendardi.cryptowallet.presentation.auth.auth_screen.AuthType
import com.akendardi.cryptowallet.presentation.auth.auth_screen.FieldType

@Composable
fun AnimatedNickNameTextField(
    authType: AuthType,
    value: String,
    error: String,
    onValueChanged: (String) -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit
) {
    val visible = authType == AuthType.SIGN_UP
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(animationSpec = tween(500)) + fadeIn(),
        exit = shrinkVertically(animationSpec = tween(500)) + fadeOut()
    ) {
        AuthTextField(
            fieldType = FieldType.USERNAME,
            value = value,
            onValueChanged = {
                onValueChanged(it)
            },
            error = error,
            onTextFieldIconClick = onTextFieldIconClick
        )
    }
}
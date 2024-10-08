package com.akendardi.cryptowallet.presentation.auth.auth_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.auth.auth_screen.AuthType
import com.akendardi.cryptowallet.presentation.auth.auth_screen.FieldType

@Composable
fun PasswordTextField(
    value: String,
    error: String,
    isPasswordVisible: Boolean = false,
    onValueChanged: (String) -> Unit,
    onPasswordIconClick: () -> Unit
) {
    val label = stringResource(R.string.password)

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        label = { Text(text = label) },

        singleLine = true,
        isError = error.isNotEmpty(),
        supportingText = {
            if (error.isNotEmpty()) {
                Text(text = error)
            }
        },
        trailingIcon = {
            IconButton(onClick = { onPasswordIconClick() }) {
                Icon(
                    imageVector = Icons.Default.RemoveRedEye,
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (!isPasswordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
}

@Composable
 fun AnimatedPasswordTextField(
    authType: AuthType,
    value: String,
    error: String,
    isPasswordVisible: Boolean = false,
    onValueChanged: (String) -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit
) {
    val visible = authType == AuthType.SIGN_IN || authType == AuthType.SIGN_UP
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(animationSpec = tween(500)) + fadeIn(),
        exit = shrinkVertically(animationSpec = tween(500)) + fadeOut()
    ) {
        PasswordTextField(
            value = value,
            onValueChanged = {
                onValueChanged(it)
            },
            isPasswordVisible = isPasswordVisible,
            error = error,
            onPasswordIconClick = {
                onTextFieldIconClick(FieldType.PASSWORD)
            }
        )
    }
}
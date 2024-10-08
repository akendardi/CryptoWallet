package com.akendardi.cryptowallet.presentation.auth.auth_screen.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.auth.auth_screen.FieldType

@Composable
fun AuthTextField(
    fieldType: FieldType,
    value: String,
    error: String,
    onValueChanged: (String) -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit
) {
    val label = if (fieldType == FieldType.USERNAME) {
        stringResource(R.string.userName)
    } else {
        stringResource(R.string.email)
    }


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
            IconButton(onClick = { onTextFieldIconClick(fieldType) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        },
        keyboardOptions = if (fieldType == FieldType.USERNAME) {
            KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        } else {
            KeyboardOptions(
                keyboardType = KeyboardType.Email
            )

        }
    )
}
package com.akendardi.cryptowallet.presentation.coin_info_screen.operations_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.presentation.theme.NegativeDifferenceColor

@Composable
fun CoinsOperationsTextField(
    isEnabled: Boolean,
    amount: String,
    onValueChanged: (String) -> Unit,
    error: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        TextField(
            enabled = isEnabled,
            value = amount,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "0.00", style = TextStyle(
                        fontSize = 36.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
                    )
                )


            },
            maxLines = 1,
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                errorTextColor = NegativeDifferenceColor,
                errorIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent

            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            isError = error != "",
            textStyle = TextStyle(
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            ),
            supportingText = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = error,
                    color = NegativeDifferenceColor,
                    textAlign = TextAlign.Center
                )
            }

        )
    }
}
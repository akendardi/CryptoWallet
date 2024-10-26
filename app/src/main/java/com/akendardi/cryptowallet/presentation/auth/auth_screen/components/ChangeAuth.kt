package com.akendardi.cryptowallet.presentation.auth.auth_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.auth.auth_screen.AuthType

@Composable
fun ChangeAuthAndReset(
    authType: AuthType,
    onAuthTypeChanged: (AuthType) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        when (authType) {
            AuthType.SIGN_IN -> {

                ChangeAuthTypeText(
                    authType = AuthType.SIGN_UP,
                    textAlign = TextAlign.Start,
                    onAuthTypeChanged = { onAuthTypeChanged(AuthType.SIGN_UP) }
                )

                ChangeAuthTypeText(
                    authType = AuthType.RESET_PASSWORD,
                    textAlign = TextAlign.End,
                    onAuthTypeChanged = { onAuthTypeChanged(AuthType.RESET_PASSWORD) }
                )

            }

            AuthType.SIGN_UP -> {
                ChangeAuthTypeText(
                    authType = AuthType.SIGN_IN,
                    textAlign = TextAlign.Center,
                    onAuthTypeChanged = { onAuthTypeChanged(AuthType.SIGN_IN) }
                )
            }

            AuthType.RESET_PASSWORD -> {
                ChangeAuthTypeText(
                    authType = AuthType.SIGN_IN,
                    textAlign = TextAlign.Center,
                    onAuthTypeChanged = { onAuthTypeChanged(AuthType.SIGN_IN) }
                )
            }
        }


    }
}

@Composable
fun ChangeAuthTypeText(
    authType: AuthType,
    textAlign: TextAlign,
    onAuthTypeChanged: (AuthType) -> Unit,
    modifier: Modifier = Modifier
) {
    val label = when (authType) {
        AuthType.SIGN_IN -> LocalContext.current.getString(R.string.sign_in)
        AuthType.SIGN_UP -> LocalContext.current.getString(R.string.sign_up)
        AuthType.RESET_PASSWORD -> LocalContext.current.getString(R.string.reset_password)
    }
    Text(
        modifier = modifier
            .clickable {
                onAuthTypeChanged(AuthType.SIGN_IN)
            },
        text = label,
        color = MaterialTheme.colorScheme.secondary,
        textAlign = textAlign,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleSmall
    )
}
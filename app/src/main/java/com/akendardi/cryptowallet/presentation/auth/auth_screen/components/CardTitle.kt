package com.akendardi.cryptowallet.presentation.auth.auth_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.auth.auth_screen.AuthType
import kotlinx.coroutines.delay

@Composable
fun CardFirstText(authType: AuthType) {

    var isTextVisible by remember { mutableStateOf(true) }
    val targetLabel = when (authType) {
        AuthType.SIGN_IN -> stringResource(R.string.sign_in)
        AuthType.SIGN_UP -> stringResource(R.string.registration)
        AuthType.RESET_PASSWORD -> stringResource(R.string.reset_password_title)
    }

    var currentLabel by remember { mutableStateOf(targetLabel) }

    LaunchedEffect(authType) {
        isTextVisible = false

        delay(100)

        currentLabel = targetLabel

        isTextVisible = true
    }
    val textAlpha by animateFloatAsState(
        targetValue = if (isTextVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 100),
        label = ""
    )
    Text(
        text = currentLabel,
        modifier = Modifier.alpha(textAlpha),
        style = MaterialTheme.typography.titleLarge
    )
}
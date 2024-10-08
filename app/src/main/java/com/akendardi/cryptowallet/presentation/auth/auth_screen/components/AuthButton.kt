package com.akendardi.cryptowallet.presentation.auth.auth_screen.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.auth.auth_screen.AuthType

@Composable
fun AuthButton(
    authType: AuthType,
    onButtonClick: () -> Unit
) {

    val animationDuration = 100

    val targetWidth = when (authType) {
        AuthType.SIGN_IN -> 150.dp
        AuthType.SIGN_UP -> 200.dp
        AuthType.RESET_PASSWORD -> 200.dp
    }

    val buttonWidth by animateDpAsState(
        targetValue = targetWidth,
        animationSpec = tween(durationMillis = animationDuration), label = ""
    )

    val authLabel = when (authType) {
        AuthType.SIGN_IN -> stringResource(R.string.sign_in)
        AuthType.SIGN_UP -> stringResource(R.string.sign_up)
        AuthType.RESET_PASSWORD -> stringResource(R.string.reset_password)
    }

    val startValue = stringResource(R.string.sign_in)

    var currentLabel by remember { mutableStateOf(startValue) }

    var isTextVisible by remember { mutableStateOf(true) }

    val textAlpha by animateFloatAsState(
        targetValue = if (isTextVisible) 1f else 0f,
        animationSpec = tween(durationMillis = animationDuration),
        finishedListener = {
            if (!isTextVisible) {
                currentLabel = authLabel
                isTextVisible = true
            }
        }, label = ""
    )

    LaunchedEffect(authType) {
        isTextVisible = false
    }

    Button(
        onClick = { onButtonClick() },
        modifier = Modifier.width(buttonWidth)
    ) {
        Text(
            text = currentLabel,
            modifier = Modifier.alpha(textAlpha),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
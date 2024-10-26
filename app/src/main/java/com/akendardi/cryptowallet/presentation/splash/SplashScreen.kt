package com.akendardi.cryptowallet.presentation.splash

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R
import kotlinx.coroutines.delay
import kotlin.system.exitProcess


@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    successLogin: () -> Unit,
    unLogin: () -> Unit,
) {
    val state = viewModel.state.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        when (val currentState = state.value) {
            SplashState.Initial -> {
                AnimateLogo()
                LaunchedEffect(Unit) {
                    viewModel.checkLogState()
                }
            }

            SplashState.NetworkError -> {
                ShowDialog()
            }

            is SplashState.Success -> {
                LaunchedEffect(Unit) {
                    Log.d("TEST_TEST", "SplashScreen: ")
                    if (currentState.nextScreen == NextScreen.Main) {
                        successLogin()
                    } else {
                        unLogin()
                    }
                }

            }
        }
    }
}


@Composable
fun ShowDialog(modifier: Modifier = Modifier) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            exitProcess(0)
        },
        title = { Text(text = stringResource(R.string.internet_error)) },
        text = { Text(text = stringResource(R.string.internet_error_description)) },
        confirmButton = {
            Button(onClick = {
                exitProcess(0)
            }) {
                Text(stringResource(R.string.exit))
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    )
}

@Composable
fun AnimateLogo(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotationValue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ), label = ""
    )
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "Логотип",
        modifier = modifier
            .fillMaxSize()
            .rotate(rotationValue)
            .wrapContentSize(Alignment.Center)
    )


}
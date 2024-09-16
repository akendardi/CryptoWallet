package com.akendardi.cryptowallet.presentation.splash

import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R
import kotlinx.coroutines.delay
import kotlin.system.exitProcess


@Composable
fun SplashScreen(
    successLogin: () -> Unit,
    unLogin: () -> Unit,
) {
    val viewModel: SplashScreenViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Логотип",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
    LaunchedEffect(Unit) {
        delay(2000)
        when(val currentState = state.value){
            SplashState.Initial -> {

            }
            SplashState.NetworkError -> {
                Log.d("TEST_TEST", "Network Error")
                showDialog = true
            }
            is SplashState.Success -> {
                if (currentState.nextScreen == NextScreen.Main){
                    successLogin()
                }else {
                    unLogin()
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                exitProcess(0)
            },
            title = { Text("Ошибка подключения") },
            text = { Text("Отсутствует подключение к интернету. Пожалуйста, проверьте настройки сети.") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    exitProcess(0)
                }) {
                    Text("Выход")
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}
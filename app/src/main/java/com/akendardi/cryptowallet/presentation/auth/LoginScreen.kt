package com.akendardi.cryptowallet.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onResetPasswordClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.lines),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .imePadding(), // Отступы для поля ввода
            verticalArrangement = Arrangement.Center, // Размещаем EmailTextField по центру
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailTextField() // Ваше поле для ввода Email
            EmailTextField() // Ваше поле для ввода Email
            Button(
                onClick = {

                }
            ) { Text("Войти в акканут") }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Создать аккаунт", modifier = Modifier.clickable {
                    onRegisterClick()
                })
                Text("Сбросить пароль", modifier = Modifier.clickable {
                    onResetPasswordClick()
                })
            }
        }


    }
}

@Preview
@Composable
fun EmailTextField() {
    val value = remember { mutableStateOf("") }

    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text("Email") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,  // Цвет рамки при фокусе
            cursorColor = MaterialTheme.colorScheme.primary,

            ),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    )
}
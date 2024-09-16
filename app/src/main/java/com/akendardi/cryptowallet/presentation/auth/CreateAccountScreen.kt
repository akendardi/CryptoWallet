package com.akendardi.cryptowallet.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R

@Composable
fun CreateAccountScreen(
    successRegisterAccount: () -> Unit,
    viewModel: CreateAccountViewModel = hiltViewModel()
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
            EmailTextField() // Ваше поле для ввода Email
            Button(
                onClick = {
                    successRegisterAccount()
                }
            ) { Text("Создать аккаунт") }

        }


    }
}

package com.akendardi.cryptowallet.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.akendardi.cryptowallet.CryptoApp
import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.repository.UserRepository
import com.akendardi.cryptowallet.presentation.theme.CryptoWalletTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CryptoApp).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            authRepository.createAccount(
                name = "Das",
                email = "email@mail.ru",
                password = "12345678"
            )
            authRepository.authState.collect{
                Log.d("COLLECTOR TEST", it.toString())
            }
        }


        setContent {
            CryptoWalletTheme {

            }
        }
    }
}


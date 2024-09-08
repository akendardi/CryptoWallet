package com.akendardi.cryptowallet.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.akendardi.cryptowallet.data.internet.api.ApiFactory
import com.akendardi.cryptowallet.presentation.theme.CryptoWalletTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val retrofit = ApiFactory
        val apiService = retrofit.apiService
        lifecycleScope.launch {
            val answer = apiService.loadlCurrencies()
            Log.d("TEST_TEST", answer.data[0].coinInfo.imageUrl)
        }

        setContent {
            CryptoWalletTheme {

            }
        }
    }
}


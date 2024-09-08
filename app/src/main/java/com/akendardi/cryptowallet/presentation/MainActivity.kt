package com.akendardi.cryptowallet.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.akendardi.cryptowallet.CryptoApp
import com.akendardi.cryptowallet.data.internet.api.ApiService
import com.akendardi.cryptowallet.presentation.theme.CryptoWalletTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as CryptoApp).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            val answer = apiService.loadHistoricalInfo(
                fsym = "BTC"
            )
            Log.d("TEST_TEST", answer.data.listPrices[0].toString())
        }

        setContent {
            CryptoWalletTheme {

            }
        }
    }
}


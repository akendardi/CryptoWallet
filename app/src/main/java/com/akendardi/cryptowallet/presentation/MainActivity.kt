package com.akendardi.cryptowallet.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.akendardi.cryptowallet.navigation.RootNavGraph
import com.akendardi.cryptowallet.presentation.theme.CryptoWalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoWalletTheme {

                val rootNavController = rememberNavController()
                RootNavGraph(rootNavController)
            }
        }
    }
}


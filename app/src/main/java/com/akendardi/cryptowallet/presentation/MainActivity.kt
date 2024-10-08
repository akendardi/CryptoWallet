package com.akendardi.cryptowallet.presentation

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.akendardi.cryptowallet.navigation.RootNavGraph
import com.akendardi.cryptowallet.presentation.theme.CryptoWalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CryptoWalletTheme {
                val window: Window = this.window
                window.navigationBarColor = MaterialTheme.colorScheme.surface.toArgb()
                val rootNavController = rememberNavController()
                RootNavGraph(rootNavController)
            }

        }
    }
}


package com.akendardi.cryptowallet.presentation

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.akendardi.cryptowallet.navigation.RootNavGraph
import com.akendardi.cryptowallet.presentation.theme.CryptoWalletTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val appViewModel: AppViewModel by viewModels {
        AppViewModelFactory(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val isDarkTheme = appViewModel.isDarkThemeFlow.collectAsState(initial = false)
            CryptoWalletTheme(darkTheme = isDarkTheme.value) {
                SetTheme(isDarkTheme.value)
                val rootNavController = rememberNavController()
                RootNavGraph(rootNavController)
            }
        }
    }

    @Composable
    fun SetTheme(isDarkTheme: Boolean) {
        val window: Window = this.window
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isDarkTheme

        systemUiController.setNavigationBarColor(
            color = MaterialTheme.colorScheme.surface,
            darkIcons = useDarkIcons
        )

        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        window.statusBarColor = Color.Transparent.toArgb()
    }
}


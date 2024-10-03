package com.akendardi.cryptowallet.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Pink40,  // Розовый для заголовков и кнопок
    secondary = Color.White, // Лиловый для графиков и акцентов
    tertiary = Pink80,  // Дополнительный розовый для иконок
    background = Color(0xFF121212), // Чёрный фон
    surface = Color(0xFF1E1E1E),   // Тёмная поверхность
    onPrimary = Color.White,       // Белый текст на розовом
    onSecondary = Color.White,     // Белый текст на лиловом
    onTertiary = Color.White,      // Белый текст на дополнительном
    onBackground = Color(0xFFE0E0E0), // Светло-серый текст на чёрном фоне
    onSurface = Color(0xFFE0E0E0),   // Светло-серый текст на тёмной поверхности
)

private val LightColorScheme = lightColorScheme(
    primary = Pink40,  // Розовый для заголовков и кнопок
    secondary = Color.Black, // Лиловый для графиков и акцентов
    tertiary = Pink80,  // Дополнительный розовый для иконок
    background = Color(0xFFFFFFFF), // Белый фон
    surface = Color(0xFFF5F5F5),   // Светлая поверхность
    onPrimary = Color.White,       // Белый текст на розовом
    onSecondary = Color(0xFF212121), // Чёрный текст на лиловом
    onTertiary = Color.White,      // Белый текст на розовом
    onBackground = Color(0xFF212121), // Тёмный текст на белом фоне
    onSurface = Color(0xFF212121),   // Тёмный текст на светлой поверхности
)



@Composable
fun CryptoWalletTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
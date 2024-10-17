package com.akendardi.cryptowallet.presentation

import android.content.res.Configuration
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.akendardi.cryptowallet.settings.SettingsManager
import com.akendardi.cryptowallet.settings.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    settingsManager: SettingsManager
): ViewModel() {
    private val themeModeFlow: Flow<ThemeMode> = settingsManager.themeModeFlow

    val isDarkThemeFlow: Flow<Boolean> = themeModeFlow.map { themeMode ->
        when (themeMode) {
            ThemeMode.LIGHT -> false
            ThemeMode.DARK -> true
            ThemeMode.SYSTEM -> isSystemInDarkTheme()
        }
    }

    private fun isSystemInDarkTheme(): Boolean {
        val uiMode = Resources.getSystem().configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return uiMode == Configuration.UI_MODE_NIGHT_YES
    }
}
package com.akendardi.cryptowallet.settings

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore("settings_preferences")

object SettingsKeys {
    val THEME_MODE = stringPreferencesKey("theme_mode")
    val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
}

class SettingsManager @Inject constructor(@ApplicationContext val context: Context) {

    val themeModeFlow: Flow<ThemeMode> = context.dataStore.data
        .map { preferences ->
            val theme = preferences[SettingsKeys.THEME_MODE] ?: ThemeMode.SYSTEM.name
            ThemeMode.valueOf(theme)
        }

    suspend fun setThemeMode(themeMode: ThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[SettingsKeys.THEME_MODE] = themeMode.name
        }
    }

    val notificationsEnabledFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            Log.d("SETTINGS_TEST", preferences[SettingsKeys.NOTIFICATIONS_ENABLED].toString())
            preferences[SettingsKeys.NOTIFICATIONS_ENABLED] ?: true
        }


    suspend fun changeNotificationsEnabled() {
        context.dataStore.edit { preferences ->
            val currentSetting = preferences[SettingsKeys.NOTIFICATIONS_ENABLED] ?: false
            preferences[SettingsKeys.NOTIFICATIONS_ENABLED] = !currentSetting
        }
    }
}
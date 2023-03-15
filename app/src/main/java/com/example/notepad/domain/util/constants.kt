package com.example.notepad.domain.util

import androidx.datastore.preferences.core.booleanPreferencesKey

const val USER_PREFERENCES = "user_preferences"

object PreferencesKeys {
    val DARK_THEME = booleanPreferencesKey("dark_theme")
}
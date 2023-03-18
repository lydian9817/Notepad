package com.example.notepad.presentation.util

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object HomeScreen: Screen("home_screen")
    object EditScreen: Screen("edit_screen")
    object  SettingsScreen: Screen("settings_screen")
}

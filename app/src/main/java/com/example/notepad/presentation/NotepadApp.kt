package com.example.notepad.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.notepad.navigation.NotepadNavHost
import com.example.notepad.ui.theme.NotepadTheme

@Composable
fun NotepadApp(
    viewModel: MainViewModel = hiltViewModel()
) {
    val preferences = viewModel.userPreferences.collectAsState()

    NotepadTheme(
        darkTheme = preferences.value.darkMode
    ) {
        val navController = rememberNavController()
        NotepadNavHost(navController = navController)
    }
}


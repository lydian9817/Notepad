package com.example.notepad.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.notepad.navigation.NotepadNavHost

@Composable
fun NotepadApp() {
    val navController = rememberNavController()
    NotepadNavHost(navController = navController)
}


package com.example.notepad.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.notepad.ui.home.HomeScreen

@Composable
fun NotepadApp() {
    val navController = rememberNavController()

    HomeScreen()
}
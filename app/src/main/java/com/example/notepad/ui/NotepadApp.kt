package com.example.notepad.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notepad.navigation.EditScreen
import com.example.notepad.navigation.HomeScreen
import com.example.notepad.ui.edit.EditScreen
import com.example.notepad.ui.home.HomeScreen

@Composable
fun NotepadApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route
    ) {
        composable(route = HomeScreen.route) {
            HomeScreen(
                onClickAddNote = { navController.navigateSingleTopTo(EditScreen.route) }
            )
        }
        composable(route = EditScreen.route) { EditScreen() }
    }
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true } //launch a single copy of the destination
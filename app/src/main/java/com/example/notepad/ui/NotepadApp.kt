package com.example.notepad.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
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
            val notepadViewModel = hiltViewModel<NotepadViewModel>() //viewModel
            HomeScreen(
                onClickAddNote = {
                    navController.navigate(EditScreen.route) {
                        launchSingleTop = true
                    }
                },
                notepadViewModel = notepadViewModel
            )
        }
        composable(route = EditScreen.route) {
            //assign parent's (HomeScreen's) viewModel
            val parentEntry = remember(it) {
                navController.getBackStackEntry(HomeScreen.route)
            }
            val parentViewModel = hiltViewModel<NotepadViewModel>(
                parentEntry
            )
            EditScreen(
                onClickSaveNote = {
                    navController.navigate(HomeScreen.route) {
                        launchSingleTop = true
                        popUpTo(HomeScreen.route)
                    }
                    //saves the note
                    parentViewModel.addNewNote(0,parentViewModel.noteTitle, parentViewModel.noteContent)
                },
                notepadViewModel = parentViewModel
            )
        }
    }
}

/*
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true } //launch a single copy of the destination
 */

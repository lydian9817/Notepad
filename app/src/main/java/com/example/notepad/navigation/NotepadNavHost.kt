package com.example.notepad.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notepad.presentation.HomeViewModel
import com.example.notepad.presentation.edit.EditScreen
import com.example.notepad.presentation.home.HomeScreen

@Composable
fun NotepadNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen.route
    ) {
        composable(route = HomeScreen.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>() //viewModel
            homeViewModel.resetStateValues()
            HomeScreen(
                onClickAddNote = {
                    navController.navigate(EditScreen.route + "/noteId") {
                        launchSingleTop = true
                    }
                },
                onNoteClick = { id ->
                    navController.navigate(EditScreen.route + "/$id") {
                        Log.i("route", "route is ${EditScreen.route + '/'}$id")
                        launchSingleTop = true
                    }
                },
                homeViewModel = homeViewModel
            )
        }
        composable(
            route = EditScreen.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true

                }
            )
        ) {
            //assign parent's (HomeScreen's) viewModel
            val parentEntry = remember(it) {
                navController.getBackStackEntry(HomeScreen.route)
            }
            val parentViewModel = hiltViewModel<HomeViewModel>(
                parentEntry
            )
            val nota = it.arguments?.getString("noteId")
            if (nota != null && nota != "noteId") {
                Log.i("nota", "el id es ${nota.toInt()}")
            }

            EditScreen(
                onClickSaveNote = {
                    navController.navigate(HomeScreen.route) {
                        launchSingleTop = true
                        popUpTo(HomeScreen.route)
                    }
                    //saves the note
                    parentViewModel.addNewNote(parentViewModel.noteTitle, parentViewModel.noteContent)
                },
                selectedNoteId = it.arguments?.getString("noteId"),
                homeViewModel = parentViewModel
            )
        }
    }
}


/*
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true } //launch a single copy of the destination
 */
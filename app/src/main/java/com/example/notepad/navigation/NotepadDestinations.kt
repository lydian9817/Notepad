package com.example.notepad.navigation

/* Destination parameters */
interface NotepadDestination {
    val route: String
}

/* Destinations */
object HomeScreen : NotepadDestination {
    override val route = "home_screen"
}

object EditScreen : NotepadDestination {
    override val route = "edit_screen"
}
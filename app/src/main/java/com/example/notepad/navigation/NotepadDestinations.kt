package com.example.notepad.navigation

/* Destination parameters */
interface NotepadDestination {
    val route: String
}

/* Destinations */
object HomeScreen : NotepadDestination {
    override val route = "home screen"
}

object EditScreen : NotepadDestination {
    override val route = "edit screen"
}
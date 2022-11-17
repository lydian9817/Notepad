package com.example.notepad.ui.home

import com.example.notepad.data.database.Note

data class HomeState(
    val notes: List<Note> = emptyList()
)
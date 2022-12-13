package com.example.notepad.presentation.home.state

import androidx.compose.runtime.mutableStateListOf
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.util.NoteOrder
import com.example.notepad.domain.util.OrderType

data class HomeState(
    val notes: List<Note> = emptyList(),
    val notesToBeDeleted: MutableList<Note> = mutableStateListOf(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending), //default order
    val isOrderDialogVisible: Boolean = false,
    val isHomeDropdownMenuOpen: Boolean = false,
    val isNoteSelectionActivated: Boolean = false,
    val isSelectionDropdownMenuOpen: Boolean = false
)
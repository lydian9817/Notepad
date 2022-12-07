package com.example.notepad.presentation.notes

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.util.NoteOrder

sealed class HomeNotesEvent {
    data class Order(val noteOrder: NoteOrder): HomeNotesEvent()
    data class DeleteNote(val note: Note): HomeNotesEvent()
    data class SelectOrUnselectNote(val note: Note): HomeNotesEvent()
    object RestoreNote: HomeNotesEvent()
    object ToggleOrderDialog: HomeNotesEvent()
    object ToggleDropdownMenu: HomeNotesEvent()
    object ToggleNoteSelection: HomeNotesEvent()
}

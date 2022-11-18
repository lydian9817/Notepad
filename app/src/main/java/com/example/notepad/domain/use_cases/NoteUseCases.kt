package com.example.notepad.domain.use_cases

//the viewModel accesses the use cases through this class
data class NoteUseCases (
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote
)
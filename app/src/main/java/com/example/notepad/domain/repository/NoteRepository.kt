package com.example.notepad.domain.repository

import com.example.notepad.domain.model.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository { //interfaces are easier to test
    suspend fun insertNote(note: Note)

    suspend fun deleteNote(notes: List<Note>)

    suspend fun getNoteById(id: Int): Note?

    fun getNotes(): Flow<List<Note>>
}


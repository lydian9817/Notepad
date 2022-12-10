package com.example.notepad.data.data_source.repository

import com.example.notepad.data.data_source.NoteDao
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl (private val noteDao: NoteDao) : NoteRepository{
    override suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun deleteNote(notes: List<Note>) {
        noteDao.delete(notes)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }
}
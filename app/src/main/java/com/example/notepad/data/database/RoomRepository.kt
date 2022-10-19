package com.example.notepad.data.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RoomRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }

    fun getNote(id: Int): LiveData<Note> {
        return noteDao.getNote(id)
    }

    fun getNotes(): LiveData<List<Note>> = noteDao.getNotes()
}

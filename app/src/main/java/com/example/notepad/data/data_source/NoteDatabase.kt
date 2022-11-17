package com.example.notepad.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notepad.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}
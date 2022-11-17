package com.example.notepad.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Note)

    @Delete
    suspend fun delete(item: Note)

    @Query("SELECT * from note_table WHERE id = :id")
    suspend fun getNote(id: Int): Note

    @Query("SELECT * from note_table ORDER BY id ASC")
    fun getNotes(): Flow<List<Note>>
}
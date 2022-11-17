package com.example.notepad.data.data_source

import androidx.room.*
import com.example.notepad.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Note)

    @Delete
    suspend fun delete(item: Note)

    @Query("SELECT * from note_table WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Query("SELECT * from note_table ORDER BY id ASC")
    fun getNotes(): Flow<List<Note>>
}
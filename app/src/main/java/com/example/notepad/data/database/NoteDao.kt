package com.example.notepad.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)//The OnConflictStrategy.IGNORE strategy ignores a new item if it's primary key is already in the database
    suspend fun insert(item: Note)

    @Update
    suspend fun update(item: Note)

    @Delete
    suspend fun delete(item: Note)

    @Query("SELECT * from note_table WHERE id = :id")
    suspend fun getNote(id: Int): Note

    @Query("SELECT * from note_table ORDER BY id ASC")
    fun getNotes(): Flow<List<Note>>
}
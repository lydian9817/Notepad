package com.example.notepad.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Nonnull @ColumnInfo(name = "note_title") val noteTitle: String,
    @Nonnull @ColumnInfo(name = "note_content") val noteContent: String,
    )

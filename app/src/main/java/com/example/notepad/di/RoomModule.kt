package com.example.notepad.di

import android.content.Context
import androidx.room.Room
import com.example.notepad.data.data_source.NoteDatabase
import com.example.notepad.data.data_source.repository.NoteRepositoryImpl
import com.example.notepad.domain.repository.NoteRepository
import com.example.notepad.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    //module set up
    private const val NOTE_DATABASE_NAME = "note_database"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.getNoteDao())
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNotes = DeleteNotes(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}
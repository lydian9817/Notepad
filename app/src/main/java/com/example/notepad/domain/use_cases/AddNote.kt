package com.example.notepad.domain.use_cases

import com.example.notepad.domain.model.InvalidNoteException
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {

    //Throws the error message if note is not valid
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.noteTitle.isBlank()) {
            throw InvalidNoteException("Please, write a title for you note!")
        }
        if(note.noteContent.isBlank()) {
            throw InvalidNoteException("Please, write a content for you note!")
        }
        noteRepository.insertNote(note)
    }
}
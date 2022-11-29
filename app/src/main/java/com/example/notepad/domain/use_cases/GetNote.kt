package com.example.notepad.domain.use_cases

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return noteRepository.getNoteById(id)
    }
}
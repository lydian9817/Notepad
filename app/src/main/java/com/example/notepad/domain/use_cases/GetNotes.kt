package com.example.notepad.domain.use_cases

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository
import com.example.notepad.domain.util.NoteOrder
import com.example.notepad.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(private val noteRepository: NoteRepository) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(orderType = OrderType.Descending) //Default order
    ): Flow<List<Note>> {
        return noteRepository.getNotes().map { notes ->
            //map the retrieved list to another, appying the desired order
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.noteTitle.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.noteTimestamp }
                    }
                }
                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.noteTitle.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.noteTimestamp }
                    }
                }
            }
        }
    }
}
package com.example.notepad.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository
import com.example.notepad.presentation.state.NotepadUiSate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotepadViewModel @Inject constructor(
    private val roomNoteRepository: NoteRepository
) : ViewModel() {

    var noteTitle by mutableStateOf("")
        private set
    var noteContent by mutableStateOf("")
        private set
    init {
        resetStateValues()
    }
    //get all notes
    val allNotes: LiveData<List<Note>> = roomNoteRepository.getNotes()

    //get one note

    fun retrieveNote(id: Int): LiveData<Note> {
        return roomNoteRepository.getNoteById(id)
    }

    fun updateStates (note: MutableState<Note?>){
        noteTitle = note.value?.noteTitle.toString()
        noteContent = note.value?.noteContent.toString()
    }

    // Notepad UI state----------
    private val _uiState = MutableStateFlow(NotepadUiSate())
    val uiState: StateFlow<NotepadUiSate> = _uiState.asStateFlow()



    fun updateNoteTitle(updatedTitle: String) {
        noteTitle = updatedTitle
    }
    fun updateNoteContent(updatedContent: String) {
        noteContent = updatedContent
    }
    //----------------------------

    //add note-------
    private fun insertNote(note: Note) {
        viewModelScope.launch {
            roomNoteRepository.insertNote(note)
        }
    }

    private fun getNewNoteEntry(noteTitle: String, noteContent: String): Note {
        return Note(
            noteTitle = noteTitle,
            noteContent = noteContent
        )
    }

    fun addNewNote(noteTitle: String, noteContent: String) {
        val newNote = getNewNoteEntry(noteTitle, noteContent)
        insertNote(newNote)
        resetStateValues()
    }
    //-----------------

    //update note------
    private fun updateNote(note: Note) {
        viewModelScope.launch {
            roomNoteRepository.updateNote(note)
        }
    }

    private fun getUpdatedNoteEntry(noteId: Int, noteTitle: String, noteContent: String): Note {
        return Note(
            id = noteId,
            noteTitle = noteTitle,
            noteContent = noteContent
        )
    }

    fun updateNote(noteId: Int, noteTitle: String, noteContent: String) {
        val updatedNote = getUpdatedNoteEntry(noteId, noteTitle, noteContent)
        updateNote(updatedNote)
    }
    //-----------------

    //delete note------
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            roomNoteRepository.deleteNote(note)
        }
    }
    //-----------------

    //check if the note has title and content in order to enable or disable the save button
    fun isNoteValid(noteTitle: String, noteContent: String): Boolean {
        return !(noteTitle.isBlank() || noteContent.isBlank())
    }
    //-----------------

    //check if the list is empty
    fun isListEmpty(): Boolean {
        return allNotes.value.isNullOrEmpty()
    }
    //-----------------

    fun resetStateValues() {
        noteTitle = ""
        noteContent= ""
    }
}

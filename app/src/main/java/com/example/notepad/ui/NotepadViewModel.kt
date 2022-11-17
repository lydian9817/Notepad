package com.example.notepad.ui

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.data.database.Note
import com.example.notepad.data.database.RoomRepository
import com.example.notepad.ui.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotepadViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        roomRepository.getNotes().onEach { notes ->
            _state.value = state.value.copy(
                notes = notes
            )
        }.launchIn(viewModelScope)
    }


    var noteTitle by mutableStateOf("")
        private set
    var noteContent by mutableStateOf("")
        private set

    //get all notes
    val allNotes: Flow<List<Note>> = roomRepository.getNotes()

    //get one note

    fun retrieveNote(id: Int): LiveData<Note> {
        return roomRepository.getNote(id)
    }

    fun updateStates (note: MutableState<Note?>){
        noteTitle = note.value?.noteTitle.toString()
        noteContent = note.value?.noteContent.toString()
    }

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
            roomRepository.insertNote(note)
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
            roomRepository.updateNote(note)
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
            roomRepository.deleteNote(note)
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
        return _state.value.notes.isEmpty()
    }
    //-----------------

    fun resetStateValues() {
        noteTitle = ""
        noteContent= ""
    }
}

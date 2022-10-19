package com.example.notepad.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.data.database.Note
import com.example.notepad.data.database.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotepadViewModel @Inject constructor (
    private val roomRepository: RoomRepository
) : ViewModel() {

    //get all notes
    val allNotes: LiveData<List<Note>> = roomRepository.getNotes()

    //get one note
    fun retrieveNote(id: Int): LiveData<Note> {
        return roomRepository.getNote(id)
    }

    //add note-------
    private fun insertNote(note: Note) {
        viewModelScope.launch {
            roomRepository.insertNote(note)
        }
    }

    private fun getNewNoteEntry(noteId: Int, noteTitle: String, noteContent: String): Note {
        return Note(noteId ,noteTitle, noteContent)
    }

    fun addNewNote(noteId: Int, noteTitle: String, noteContent: String) {
        val newNote = getNewNoteEntry(noteId, noteTitle, noteContent)
        insertNote(newNote)
    }
    //-----------------

    //update note------
    private fun updateNote(note: Note) {
        viewModelScope.launch {
            roomRepository.updateNote(note)
        }
    }

    private fun getUpdatedNoteEntry(noteId: Int, noteTitle: String, noteContent: String): Note {
    return Note(noteId, noteTitle, noteContent)
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
        return allNotes.value?.isEmpty() != true
    }

}
 /*
private val _noteTitle = MutableLiveData<String>()
val noteTitle: LiveData<String> = _noteTitle

private val _noteContent = MutableLiveData<String>()
val noteContent: LiveData<String> = _noteContent

private val _saveEnabled = MutableLiveData<Boolean>()
val saveEnabled: LiveData<Boolean> = _saveEnabled

private fun onNoteChanged(noteTitle: String, noteContent: String) {
    _noteTitle.value = noteTitle
    _noteContent.value = noteContent
    _saveEnabled.value = _noteTitle.value != "" && _noteContent.value != ""
}
 */
package com.example.notepad.presentation.edit

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.R
import com.example.notepad.domain.model.InvalidNoteException
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.use_cases.NoteUseCases
import com.example.notepad.presentation.edit.state.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle,
    @SuppressLint("StaticFieldLeak") @ApplicationContext private val context: Context
) : ViewModel() {

    //Backing properties
    private val _noteTitle = mutableStateOf(
        TextFieldState(
            hint = context.getString(R.string.edit_screen_title_text_field_hint)
        )
    )
    val noteTitle: State<TextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        TextFieldState(
            hint = context.getString(R.string.edit_screen_content_text_field_hint)
        )
    )
    val noteContent: State<TextFieldState> = _noteContent

    private var currentNoteId: Int? = null

    //the eventFlow sends events of type UIEvent
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        //retrieve the id, if there is one, and update the textfields' state
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.noteTitle,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.noteContent,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: EditNoteEvent) {
        when (event) {
            is EditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is EditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is EditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is EditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteContent.value.text.isBlank()
                )
            }
            is EditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                noteTitle = noteTitle.value.text,
                                noteContent = noteContent.value.text,
                                noteTimestamp = System.currentTimeMillis(),
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: context.getString(R.string.edit_screen_error_message)
                            )
                        )
                    }
                }
            }
        }
    }


    //this class handles the showsnackbar and save note actions
    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}
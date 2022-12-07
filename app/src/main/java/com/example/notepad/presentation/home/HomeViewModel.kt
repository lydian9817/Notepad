package com.example.notepad.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.domain.model.Note
import com.example.notepad.domain.use_cases.NoteUseCases
import com.example.notepad.domain.util.NoteOrder
import com.example.notepad.domain.util.OrderType
import com.example.notepad.presentation.notes.HomeNotesEvent
import com.example.notepad.presentation.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**The ViewModel interact with the use cases, which contains the business logic,
and then, pass the results (states) to the UI*/
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    //This contains the values observed by the UI
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    //The recently deleted note reference, in order to restore it
    private var recentlyDeletedNote: Note? = null

    //Coroutine job
    private var getNotesJob: Job? = null

    //initially loads the list and sort it by date, in a descending order
    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    //This function is called from the UI
    fun onEvent(event: HomeNotesEvent) {
        when (event) {
            is HomeNotesEvent.Order -> {
                //compares de class used by the ui to the event one. The order changes according
                //to that comparison. For example, if the order and order type are equals,
                // nothing is done (return)
                if (
                    state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType::class == event.noteOrder.orderType::class
                ) {
                    return
                }
                getNotes(event.noteOrder)

            }
            is HomeNotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is HomeNotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is HomeNotesEvent.ToggleOrderDialog -> {
                //copies the "ui side" value and invert it
                _state.value = state.value.copy(
                    isOrderDialogVisible = !state.value.isOrderDialogVisible,
                    isDropdownMenuOpen = false
                )
            }
            is HomeNotesEvent.ToggleDropdownMenu -> {
                _state.value = state.value.copy(
                    isDropdownMenuOpen = !state.value.isDropdownMenuOpen
                )
            }
            is HomeNotesEvent.ToggleNoteSelection -> {
                _state.value = state.value.copy(
                    isNoteSelectionActivated = !state.value.isNoteSelectionActivated
                )
            }
            is HomeNotesEvent.SelectOrUnselectNote -> {
                if (_state.value.notesToBeDeleted.contains(event.note)) {
                    _state.value.notesToBeDeleted.remove(event.note)
                } else {
                    _state.value.notesToBeDeleted.add(event.note)
                }
            }
        }
    }

    //First, this cancels the coroutine job in case it is running, and then set it to the getNotes use case.
    //Then, on each note list emission, _state changes its value (the list) to the ui side state variable,
    // so that it cannot be modified again in recompositions. Finally launch this on a coroutine
    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes, noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }
}

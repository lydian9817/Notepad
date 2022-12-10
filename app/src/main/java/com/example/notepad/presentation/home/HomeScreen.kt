package com.example.notepad.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notepad.presentation.home.components.*
import com.example.notepad.presentation.notes.HomeNotesEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClickAddNote: () -> Unit = {},
    onNoteClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            if (state.isNoteSelectionActivated) {
                SelectedNoteAppBar(
                    onCloseClick = { viewModel.onEvent(HomeNotesEvent.ToggleNoteSelection) },
                    onDeleteClick = {
                        viewModel.onEvent(HomeNotesEvent.DeleteNotes(state.notesToBeDeleted))
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Note Deleted",
                                actionLabel = "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(HomeNotesEvent.RestoreNote)
                            }
                        }
                    },
                    enableButton = state.notesToBeDeleted.isNotEmpty()
                )
            } else {
                AppBar(
                    isMenuOpen = state.isDropdownMenuOpen,
                    updateShowMenu = {
                        viewModel.onEvent(HomeNotesEvent.ToggleDropdownMenu)
                    },
                    updateShowDialog = {
                        viewModel.onEvent(HomeNotesEvent.ToggleOrderDialog)
                    }
                )
            }
        },
        floatingActionButton = {
            HomeFloatingActionButton(onClickAdd = onClickAddNote)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        //EmptyNoteList()
        if (state.notes.isEmpty()) {
            EmptyNoteList()
        } else {
            NoteList(
                scaffoldPadding = padding,
                notes = state.notes,
                onNoteClick = onNoteClick,
                onSelectNote = { note ->
                    viewModel.onEvent(HomeNotesEvent.SelectOrUnselectNote(note))
                },
                onLongClick = { note ->
                    viewModel.onEvent(HomeNotesEvent.ToggleNoteSelection)
                    state.notesToBeDeleted.add(note)
                              },
                isNoteSelectionActivated = state.isNoteSelectionActivated,
                isNoteSelected = { note ->
                    state.notesToBeDeleted.contains(note)
                }
            )
        }
        if (state.isOrderDialogVisible) {
            OrderDialog(
                updateShowDialog = { viewModel.onEvent(HomeNotesEvent.ToggleOrderDialog) },
                noteOrder = state.noteOrder,
                onOrderChange = {
                    viewModel.onEvent(HomeNotesEvent.Order(it))
                    viewModel.onEvent(HomeNotesEvent.ToggleOrderDialog)
                }
            )
        }
    }
}




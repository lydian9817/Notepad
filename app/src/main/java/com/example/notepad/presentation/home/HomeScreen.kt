package com.example.notepad.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notepad.domain.model.Note
import com.example.notepad.presentation.home.components.OrderDialog
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
            AppBar(
                isMenuOpen = state.isDropdownMenuOpen,
                updateShowMenu = {
                    viewModel.onEvent(HomeNotesEvent.ToggleDropdownMenu)
                },
                updateShowDialog = {
                    viewModel.onEvent(HomeNotesEvent.ToggleOrderDialog)
                }
            )
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
                onDelete = { note ->
                    viewModel.onEvent(HomeNotesEvent.DeleteNote(note))
                    //Snack bars should be launched from a coroutine
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Note Deleted",
                            actionLabel = "Undo"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(HomeNotesEvent.RestoreNote)
                        }
                    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    updateShowMenu: () -> Unit,
    isMenuOpen: Boolean,
    updateShowDialog: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "navigation icon",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },
        title = {
            Text("Home Screen")
        },
        actions = {
            IconButton(onClick = updateShowMenu) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "settings icon",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
            DropdownMenu(
                expanded = isMenuOpen,
                onDismissRequest = updateShowMenu,
                modifier = Modifier.padding(5.dp)
            ) {
                DropdownMenuItem(
                    onClick = updateShowDialog,
                    text = { Text(text = "Sort by") }
                )
            }
        }
    )
}


@Composable
fun HomeFloatingActionButton(onClickAdd: () -> Unit) {
    FloatingActionButton(
        shape = CircleShape,
        onClick = onClickAdd
    ) {
        Icon(Icons.Rounded.Add, contentDescription = "Add Note")
    }
}

@Composable
fun EmptyNoteList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tap + to write a new note!",
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteList(
    scaffoldPadding: PaddingValues,
    notes: List<Note>,
    onNoteClick: (Int) -> Unit = {},
    onDelete: (Note) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
    ) {
        items(notes) { note ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                NoteItem(
                    note = note,
                    onDelete = onDelete,
                    modifier = Modifier.combinedClickable(
                        onClick = { onNoteClick(note.id!!) },
                        onLongClick = { /**/ }
                    )
                )
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    onDelete: (Note) -> Unit,
    modifier: Modifier
) {
    Surface(
        border = BorderStroke(
            width = Dp.Hairline,
            color = Color.Black
        )
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = note.noteTitle,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = note.noteContent,
                style = MaterialTheme.typography.bodyMedium
            )
            IconButton(
                onClick = { onDelete(note) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "delete note"
                )
            }
        }
    }
}



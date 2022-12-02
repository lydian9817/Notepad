package com.example.notepad.presentation.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notepad.domain.model.Note
import com.example.notepad.presentation.home.components.OrderDialogBox
import com.example.notepad.presentation.notes.HomeNotesEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")//evita el error de padding
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
    ) {
        //EmptyNoteList()
        if (state.notes.isEmpty()) {
            EmptyNoteList()
        } else {
            NoteList(
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
            OrderDialogBox(
                updateShowDialog = { viewModel.onEvent(HomeNotesEvent.ToggleOrderDialog) },
                noteOrder = state.noteOrder,
                onOrderChange = { viewModel.onEvent(HomeNotesEvent.Order(it)) }
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
    val context = LocalContext.current
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
            //Box {
            IconButton(onClick = updateShowMenu) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "settings icon",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

            }
            //}
            DropdownMenu(
                expanded = isMenuOpen,
                onDismissRequest = updateShowMenu,
                modifier = Modifier.padding(5.dp)
            ) {
                DropdownMenuItem(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Toast",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    text = { Text(text = "Toast") }
                )
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

@Composable
fun NoteList(
    notes: List<Note>,
    onNoteClick: (Int) -> Unit = {},
    onDelete: (Note) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(notes) { note ->
            NoteItem(
                note = note,
                onDelete = onDelete,
                modifier = Modifier.clickable {
                    onNoteClick(note.id!!)
                }
            )
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    onDelete: (Note) -> Unit,
    modifier: Modifier
) {
    Surface() {
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



package com.example.notepad.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notepad.data.SampleData
import com.example.notepad.data.database.Note
import com.example.notepad.ui.NotepadViewModel
import com.example.notepad.ui.theme.NotepadTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")//evita el error de padding
@Composable
fun HomeScreen(onClickAddNote: () -> Unit = {}, notepadViewModel: NotepadViewModel) {
    val notes = notepadViewModel.allNotes.observeAsState(listOf())
    val isListEmpty = notepadViewModel.isListEmpty()
    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = { HomeFloatingActionButton( onClickAdd = onClickAddNote ) }
    ) {
        //EmptyNoteList()
        if (isListEmpty) {
            EmptyNoteList()
        } else {
            NoteList(notes = notes)
        }

    }

}

@Composable
fun AppBar() {
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
        }
    )
}

@Composable
fun HomeFloatingActionButton(onClickAdd: () -> Unit) {
    FloatingActionButton(
        shape = CircleShape,
        onClick = onClickAdd
    ) {
        Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Note")
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
            style = MaterialTheme.typography.h6,
        )
    }
}

@Composable
fun NoteList(notes: State<List<Note>?>) {
        LazyColumn {
            notes.value?.size?.let {
                items(it) {
                    Note(notes.value!![it])

                }
            }
        }
    }

@Composable
fun Note(note: Note) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = note.noteTitle, style = MaterialTheme.typography.subtitle2 )
        Text(text = note.noteContent, style = MaterialTheme.typography.body2)
        Divider()
    }
}
/*
@Preview
@Composable
fun NoteListPreview() {
    NotepadTheme() {
        NoteList()
    }

}

 */


/*
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    NotepadTheme {
        HomeScreen()
    }
}
 */


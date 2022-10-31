package com.example.notepad.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notepad.ui.NotepadViewModel
import com.example.notepad.ui.home.AppBar
import com.example.notepad.ui.notes.TextNoteEdit
import com.example.notepad.ui.theme.NotepadTheme

@Composable
fun EditScreen(onClickSaveNote: () -> Unit = {}, notepadViewModel: NotepadViewModel) {
    var noteTitle by rememberSaveable { mutableStateOf("") }
    var noteContent by rememberSaveable { mutableStateOf("") }
    val isNoteValid = notepadViewModel.isNoteValid(noteTitle, noteContent)


    Column {
        IconRow()
        TextNoteEdit(
            title = noteTitle,
            onTitleChange = { noteTitle = it },
            content = noteContent,
            onContentChange = { noteContent = it })
        Spacer(modifier = Modifier.height(30.dp))
        SaveButton(onClickSave = onClickSaveNote, isEnabled = isNoteValid)
    }
}

@Composable
fun IconRow() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Done,
                    contentDescription = "Save button",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },
        title = {
            Text("Edit Screen") //TODO poner la pantalla actual como string
        }
    )
}

@Composable
fun SaveButton(onClickSave: () -> Unit, isEnabled: Boolean) {
    Button(onClick = onClickSave, enabled = isEnabled) {
        Text("Save Note")
    }
}

/*
@Preview
@Composable
fun EditScreenPreview() {
    NotepadTheme() {
        EditScreen()
    }
}
 */
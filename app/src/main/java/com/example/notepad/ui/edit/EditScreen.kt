package com.example.notepad.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notepad.ui.NotepadViewModel
import com.example.notepad.ui.notes.TextNoteEdit

@Composable
fun EditScreen(onClickSaveNote: () -> Unit = {}, notepadViewModel: NotepadViewModel) {
    //var noteTitle by rememberSaveable { mutableStateOf("") }
    //var noteContent by rememberSaveable { mutableStateOf("") }
    val notepadUiState by notepadViewModel.uiState.collectAsState()
    val isNoteValid = notepadViewModel.isNoteValid(notepadViewModel.noteTitle, notepadViewModel.noteContent)


    Column {
        IconRow()
        TextNoteEdit(
            title = notepadViewModel.noteTitle,
            onTitleChange = { notepadViewModel.updateNoteTitle(it) },
            content = notepadViewModel.noteContent,
            onContentChange = { notepadViewModel.updateNoteContent(it)}
        )
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
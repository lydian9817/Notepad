package com.example.notepad.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notepad.ui.NotepadViewModel
import com.example.notepad.ui.notes.TextNoteEdit
import com.example.notepad.ui.theme.NotepadTheme

@Composable
fun EditScreen(onClickSaveNote: () -> Unit = {}, notepadViewModel: NotepadViewModel) {
    var noteTitle by rememberSaveable { mutableStateOf("") }
    var noteContent by rememberSaveable { mutableStateOf("") }
    val isNoteValid = notepadViewModel.isNoteValid(noteTitle, noteContent)

    Column {
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
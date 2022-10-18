package com.example.notepad.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notepad.ui.notes.TextNoteEdit
import com.example.notepad.ui.theme.NotepadTheme

@Composable
fun EditScreen(onClickSaveNote: () -> Unit = {}) {
    Column {
        TextNoteEdit()
        Spacer(modifier = Modifier.height(30.dp))
        SaveButton(onClickSave = onClickSaveNote)
    }
}

@Composable
fun SaveButton(onClickSave: () -> Unit) {
    Button(onClick = onClickSave) {
        Text("Save Note")
    }
}

@Preview
@Composable
fun EditScreenPreview() {
    NotepadTheme() {
        EditScreen()
    }
}
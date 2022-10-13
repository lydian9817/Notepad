package com.example.notepad.ui.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.notepad.ui.theme.NotepadTheme

@Composable
fun TextNoteEdit() {
    Column {
        Title()
        Divider()
        TextNoteContent()
    }

}

@Composable
fun Title() {
    var title by rememberSaveable { mutableStateOf("") }
    TextField(
        value = title,
        onValueChange = { title = it },
        label = { Text("Title") },
        singleLine = true,
        placeholder = { Text("Note title") }
    )
}

@Composable
fun TextNoteContent() {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Start typing!") }
    )
}

@Preview
@Composable
fun TextNoteEditPreview() {
    NotepadTheme {
        TextNoteEdit()
    }
}



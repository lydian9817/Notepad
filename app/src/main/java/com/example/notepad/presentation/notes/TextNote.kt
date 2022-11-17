package com.example.notepad.presentation.notes

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
fun TextNoteEdit(
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit
) {
    Column {
        Title(title, onTitleChange)
        Divider()
        TextNoteContent(content, onContentChange)
    }

}

@Composable
fun Title(title: String, onTitleChange: (String) -> Unit) {
    TextField(
        value = title,
        onValueChange = onTitleChange,
        label = { Text("Title") },
        singleLine = true,
        placeholder = { Text("Note title") }
    )
}

@Composable
fun TextNoteContent(content: String, onContentChange: (String) -> Unit) {
    TextField(
        value = content,
        onValueChange = onContentChange,
        placeholder = { Text("Start typing!") }
    )
}

/*
@Preview
@Composable
fun TextNoteEditPreview() {
    NotepadTheme {
        TextNoteEdit()
    }
}

 */



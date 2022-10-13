package com.example.notepad.ui.edit

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notepad.ui.notes.TextNoteEdit
import com.example.notepad.ui.theme.NotepadTheme

@Composable
fun EditScreen() {
    TextNoteEdit()
}

@Preview
@Composable
fun EditScreenPreview() {
    NotepadTheme() {
        EditScreen()
    }
}
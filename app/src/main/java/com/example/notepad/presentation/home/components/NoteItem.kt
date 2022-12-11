package com.example.notepad.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notepad.domain.model.Note

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier
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
    }
}

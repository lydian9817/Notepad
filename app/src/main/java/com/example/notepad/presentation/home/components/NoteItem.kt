package com.example.notepad.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.notepad.domain.model.Note

@Composable
fun NoteItem(
    note: Note,
    onDelete: (Note) -> Unit,
    modifier: Modifier
) {
    Surface(
        border = BorderStroke(
            width = Dp.Hairline,
            color = Color.Black
        )
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
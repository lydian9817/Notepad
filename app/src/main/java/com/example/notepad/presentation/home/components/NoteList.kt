package com.example.notepad.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notepad.domain.model.Note

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteList(
    scaffoldPadding: PaddingValues,
    notes: List<Note>,
    onNoteClick: (Int) -> Unit = {},
    onSelectNote: (Note) -> Unit,
    onLongClick: (Note) -> Unit,
    isNoteSelectionActivated: Boolean,
    isNoteSelected: (Note) -> Boolean
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
    ) {
        items(notes) { note ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .combinedClickable(
                        onClick = {
                            if (isNoteSelectionActivated) {
                                onSelectNote(note)
                            } else {
                                onNoteClick(note.id!!)
                            }
                        },
                        onLongClick = { onLongClick(note) }
                    )
            ) {
                if (isNoteSelectionActivated) {
                    SelectCircle(
                        isNoteSelected = isNoteSelected(note)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                NoteItem(
                    note = note,
                    modifier = Modifier
                )
            }
        }
    }
}
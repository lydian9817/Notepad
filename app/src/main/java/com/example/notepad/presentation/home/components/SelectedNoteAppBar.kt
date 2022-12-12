package com.example.notepad.presentation.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notepad.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedNoteAppBar(
    onCloseClick: () -> Unit,
    onDeleteClick: () -> Unit,
    enableButton: Boolean,
    count: Int
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = stringResource(R.string.select_appbar_close_note_selection),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp)
                )
            }
        },
        title = {
            Text(stringResource(R.string.selected_appbar_count, count))
        },
        actions = {
            IconButton(
                onClick = onDeleteClick,
                enabled = enableButton
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = stringResource(R.string.select_appbar_delete_notes),
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    )
}

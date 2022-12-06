package com.example.notepad.presentation.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedNoteAppBar(
    onCloseClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "navigation icon",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp)
                )
            }
        },
        title = {
            Text("Selected")
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete Selected notes",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    )
}

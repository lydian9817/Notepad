package com.example.notepad.presentation.edit.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onClickBackButton:  () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onClickBackButton) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },
        title = {
            Text("Edit Screen") //TODO poner la pantalla actual como string
        }
    )
}
package com.example.notepad.presentation.edit.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notepad.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onClickBackButton:  () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onClickBackButton) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.edit_screen_back_button),
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },
        title = {
            Text(stringResource(R.string.edit_screen_appbar_title))
        }
    )
}
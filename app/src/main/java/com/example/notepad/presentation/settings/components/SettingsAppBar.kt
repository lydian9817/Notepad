package com.example.notepad.presentation.settings.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.notepad.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopAppBar(
    onGoBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.settings_screen_appbar_title))
        },
        navigationIcon = {
            IconButton(
                onClick = onGoBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
        }
    )
}
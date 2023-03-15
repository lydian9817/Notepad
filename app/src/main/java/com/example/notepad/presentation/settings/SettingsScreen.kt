package com.example.notepad.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.notepad.R
import com.example.notepad.presentation.settings.components.BooleanOptionItem
import com.example.notepad.presentation.settings.components.SettingsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onGoBackClick: () -> Unit
) {

    Scaffold(
        topBar = {
            SettingsTopAppBar(
                onGoBackClick = onGoBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
        ) {
            BooleanOptionItem(
                onClick = { /*TODO*/ },
                checked = true,
                text = stringResource(R.string.settings_screen_dark_mode)
            )
        }
    }
}
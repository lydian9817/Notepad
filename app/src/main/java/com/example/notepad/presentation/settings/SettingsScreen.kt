package com.example.notepad.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notepad.R
import com.example.notepad.domain.util.PreferencesKeys
import com.example.notepad.presentation.settings.components.BooleanOptionItem
import com.example.notepad.presentation.settings.components.SettingsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onGoBackClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val preferences = viewModel.userPreferences.collectAsState()

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
                onClick = {
                    viewModel.saveBooleanPreference(
                        key = PreferencesKeys.DARK_THEME,
                        value = !preferences.value.darkMode
                    )
                },
                checked = preferences.value.darkMode,
                text = stringResource(R.string.settings_screen_dark_mode)
            )
        }
    }
}
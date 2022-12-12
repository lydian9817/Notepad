package com.example.notepad.presentation.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notepad.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    updateShowMenu: () -> Unit,
    isMenuOpen: Boolean,
    updateShowDialog: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = stringResource(R.string.home_navigation_icon),
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },
        title = {
            Text(stringResource(R.string.home_screen_appbar_title))
        },
        actions = {
            IconButton(onClick = updateShowMenu) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = stringResource(R.string.home_settings_icon),
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
            DropdownMenu(
                expanded = isMenuOpen,
                onDismissRequest = updateShowMenu,
                modifier = Modifier.padding(5.dp)
            ) {
                DropdownMenuItem(
                    onClick = updateShowDialog,
                    text = { Text(text = stringResource(R.string.home_sort_by_dm_item)) }
                )
            }
        }
    )
}
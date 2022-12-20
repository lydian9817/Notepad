package com.example.notepad.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notepad.R

@Composable
fun HomeFloatingActionButton(
    onClickAdd: () -> Unit,
    visible: Boolean
) {
    AnimatedVisibility(
        visible = !visible,
        enter = slideInHorizontally(
            initialOffsetX = { it * 2 }
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it * 2 }
        )
    ) {
        FloatingActionButton(
            shape = RoundedCornerShape(16.dp),
            onClick = onClickAdd
        ) {
            Icon(Icons.Rounded.Add, contentDescription = stringResource(R.string.home_fab_description))
        }
    }

}
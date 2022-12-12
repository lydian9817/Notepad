package com.example.notepad.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notepad.R

@Composable
fun SelectCircle(
    isNoteSelected: Boolean
) {
    Box(
        modifier = Modifier
            .height(24.dp)
            .width(24.dp)
    ) {
        if (isNoteSelected) {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = stringResource(R.string.select_circle_checked_description),
                tint = Color.Green,
                modifier = Modifier.clickable(
                    enabled = false,
                    onClick = {}
                )
            )
        } else {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_baseline_circle_24
                ),
                contentDescription = stringResource(R.string.select_circle_unchecked_description),
                modifier = Modifier.clickable(
                    enabled = false,
                    onClick = {}
                )
            )
        }

    }
}
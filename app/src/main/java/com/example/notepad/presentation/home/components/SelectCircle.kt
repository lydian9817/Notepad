package com.example.notepad.presentation.home.components

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
                contentDescription = "note selected",
                tint = Color.Green
            )
        } else {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_baseline_circle_24
                ), contentDescription = "note is not selected"
            )
        }

    }
}
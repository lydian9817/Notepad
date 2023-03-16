package com.example.notepad.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notepad.R

@Composable
fun SplashScreen() {

    Box(
        modifier = Modifier
            .background(color = Color(0xFF6200EE))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_custom_foreground),
            contentDescription = null,
            modifier = Modifier.size(288.dp, 288.dp)
        )
    }
}

@Preview
@Composable
fun PrevSplash() {
    SplashScreen()
}
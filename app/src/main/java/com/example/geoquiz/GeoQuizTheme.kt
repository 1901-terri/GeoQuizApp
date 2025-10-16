package com.example.geoquiz

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun GeoQuizTheme(content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        background = Color.White,
        onBackground = Color.Black,
        primary = Color(0xFF6200EE),
        onPrimary = Color.White
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
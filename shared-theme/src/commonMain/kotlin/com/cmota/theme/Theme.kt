package com.cmota.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = BLUE,
    secondary = GRAY,
    surface = WHITE,
)

@Composable
fun MultiplatformTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = LightColorPalette,
        typography = TextTypography,
        shapes = Shapes,
        content = content
    )
}
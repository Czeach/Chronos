package com.example.chronos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = White500,
    primaryVariant = White200,
    onPrimary = Grey200,
    background = Navy500,
    onBackground = Navy700,
    secondary = Red200,
    secondaryVariant = Red500,
    surface = Purple200,
    onSurface = Purple500,
)

private val LightColorPalette = lightColors(
    primary = White500,
    primaryVariant = White200,
    onPrimary = Grey200,
    background = Navy500,
    onBackground = Navy700,
    secondary = Red200,
    secondaryVariant = Red500,
    surface = Purple200,
    onSurface = Purple500,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ChronosTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
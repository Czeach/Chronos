package com.czech.chronos.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    background = Navy500,
    primary = White500,
    secondary = Red200,
    inversePrimary = White200,
    tertiary = Grey200,
    surface = Navy700,
    inverseSurface = Purple200,

    // Constants
    secondaryContainer = Red500,
    inverseOnSurface = Navy700,
    primaryContainer = Teal200,
    outlineVariant = Yellow200,
    tertiaryContainer = Purple300

)

private val LightColorScheme = lightColorScheme(
    background = White500,
    primary = Grey900,
    secondary = Navy500,
    inversePrimary = Purple200,
    tertiary = Grey200,
    surface = White900,
    inverseSurface = Grey500,

    // Constants
    secondaryContainer = Red500,
    inverseOnSurface = Navy700,
    primaryContainer = Teal200,
    outlineVariant = Yellow200,
    tertiaryContainer = Purple300

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
fun ChronosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {


    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.surface.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
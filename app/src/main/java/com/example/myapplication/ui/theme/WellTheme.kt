package com.example.myapplication.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

@Composable
fun BaseWellTheme(colorScheme: ColorScheme, extendedThemeColors: ExtendedColors? = null, content: @Composable () -> Unit) {
    val extendedColors =  remember {
        extendedThemeColors ?: when (colorScheme) {
            lightThemeColors -> {
                lightExtendedColors
            }

            else -> {
                darkExtendedColors
            }
        }
    }
    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}

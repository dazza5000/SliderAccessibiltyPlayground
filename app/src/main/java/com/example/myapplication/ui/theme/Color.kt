package com.example.myapplication.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val wellBlue = Color(0xff0556FF)
private val textDark = Color(0xff1A051D)
private val blueSteel = Color(0xff4C5870)
private val paleSmoke = Color(0xffF5F5F5)
private val paleAzure = Color(0xffF0F5F6)
private val waterloo = Color(0xff7B8497)
private val tangerine = Color(0xffED6E0C)
private val errorLight = Color(0xffF96175)
private val salmonMedium = Color(0xffAF2B41)
private val whisper = Color(0xffE7E7E7)
private val cornflowerLight = Color(0xff87C0FF)
private val goldenrodLight = Color(0xffF8D158)
private val seaFoam = Color(0xff48D8B2)

val Color.Companion.BlueSteel: Color
    get() = blueSteel
val Color.Companion.WellBlue: Color
    get() = wellBlue
val Color.Companion.GoldenrodLight: Color
    get() = goldenrodLight
val Color.Companion.TextDark: Color
    get() = textDark
val Color.Companion.PaleAzure: Color
    get() = paleAzure
val Color.Companion.PaleSmoke: Color
    get() = paleSmoke
val Color.Companion.Waterloo: Color
    get() = waterloo
val Color.Companion.BlueDarkest: Color
    get() = Color(0xff002F8C)
val Color.Companion.BlueDarker: Color
    get() = Color(0xff0045D9)
val Color.Companion.BlueLighter: Color
    get() = Color(0xff7AA7FF)
val Color.Companion.Success: Color
    get() = Color(0xff00C48C)

val lightExtendedColors = ExtendedColors(
    blueSteel = blueSteel,
    waterloo = waterloo,
    paleSmoke = paleSmoke,
    whisper = whisper,
    warning = tangerine,
)

val darkExtendedColors = lightExtendedColors.copy(
    blueSteel = Color.White,
    waterloo = Color.White,
    paleSmoke = Color.White,
    whisper = Color.White,
)

val lightThemeColors = lightColorScheme(
    primary = wellBlue,
    onPrimary = Color.White,
    secondary = wellBlue,
    onSecondary = Color.White,
    secondaryContainer = Color.White,
    onSecondaryContainer = wellBlue,
    error = salmonMedium,
    onBackground = textDark,
    onSurface = textDark,
    surfaceTint = Color.White,
)

val darkThemeColors = darkColorScheme(
    primary = wellBlue,
    onPrimary = Color.White,
    secondary = wellBlue,
    onSecondary = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    onBackground = Color.White,
    outline = Color.White,
    error = salmonMedium,
)

val blueThemeColors = getThemeColors(themeColor = wellBlue, darkTheme = true)

val blueSteelThemeColors = getThemeColors(themeColor = blueSteel, darkTheme = true)

val seaFoamThemeColors = lightThemeColors.copy(
    primary = textDark,
    onPrimary = seaFoam,
    secondary = textDark,
    onSecondary = seaFoam,
    surface = seaFoam,
    background = seaFoam,
)

fun getThemeColors(
    themeColor: Color,
    darkTheme: Boolean = false,
) = if (darkTheme) {
    darkThemeColors.copy(
        primary = Color.White,
        onPrimary = themeColor,
        secondary = Color.White,
        onSecondary = themeColor,
        surface = themeColor,
        background = themeColor,
    )
} else {
    lightThemeColors.copy(
        primary = themeColor,
        secondary = themeColor,
        onSecondaryContainer = themeColor,
    )
}

data class ExtendedColors(
    val blueSteel: Color,
    val waterloo: Color,
    val paleSmoke: Color,
    val whisper: Color,
    val warning: Color,
)

val ColorScheme.extended: ExtendedColors
    @Composable
    get() = LocalExtendedColors.current

val LocalExtendedColors = staticCompositionLocalOf {
    lightExtendedColors
}


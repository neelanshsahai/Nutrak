package com.example.nutrak.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val LightColorScheme = NutrakColorScheme(
    background = backgroundColorWhite,
    onBackground = backgroundColorDark,
    introRadialStart = introBackgroundRadialGradientStartLight,
    introRadialMid = introBackgroundRadialGradientMidLight,
    introRadialEnd = introBackgroundRadialGradientEndLight,
    introLinearStart = introBackgroundLinearGradientStartLight,
    introLinearMid = introBackgroundLinearGradientMidLight,
    introLinearEnd = introBackgroundLinearGradientEndLight,
    paginationActive = paginationActiveLight,
    paginationInactive = paginationInactiveLight,
)

private val DarkColorScheme = NutrakColorScheme(
    background = backgroundColorDark,
    onBackground = backgroundColorWhite,
    introRadialStart = introBackgroundRadialGradientStartDark,
    introRadialMid = introBackgroundRadialGradientMidDark,
    introRadialEnd = introBackgroundRadialGradientEndDark,
    introLinearStart = introBackgroundLinearGradientStartDark,
    introLinearMid = introBackgroundLinearGradientMidDark,
    introLinearEnd = introBackgroundLinearGradientEndDark,
    paginationActive = paginationActiveDark,
    paginationInactive = paginationInactiveDark,
)

@Composable
fun NutrakTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        content = content,
    )
}

object AppTheme {
    val colorScheme: NutrakColorScheme
        @Composable get() = LocalAppColorScheme.current
}
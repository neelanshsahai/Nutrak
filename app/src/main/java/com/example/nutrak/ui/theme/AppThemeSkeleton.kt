package com.example.nutrak.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class NutrakColorScheme(
    // Common Colors
    val background: Color,
    val onBackground: Color,

    // Intro Screen Colors
    val introRadialStart: Color,
    val introRadialMid: Color,
    val introRadialEnd: Color,
    val introLinearStart: Color,
    val introLinearMid: Color,
    val introLinearEnd: Color,
    val paginationActive: Color,
    val paginationInactive: Color,

    // Login Screen Colors
    val iconButtonBorder: Color,
    val divider: Color,
    val dividerText: Color,
)

val LocalAppColorScheme = staticCompositionLocalOf {
    NutrakColorScheme(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        introRadialStart = Color.Unspecified,
        introRadialMid = Color.Unspecified,
        introRadialEnd = Color.Unspecified,
        introLinearStart = Color.Unspecified,
        introLinearMid = Color.Unspecified,
        introLinearEnd = Color.Unspecified,
        paginationActive = Color.Unspecified,
        paginationInactive = Color.Unspecified,
        iconButtonBorder = Color.Unspecified,
        divider = Color.Unspecified,
        dividerText = Color.Unspecified,
    )
}

val LocalTextField = staticCompositionLocalOf {

}
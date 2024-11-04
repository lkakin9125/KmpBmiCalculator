package com.example.bmicalculator.android.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class BmiColor(
    val contentDanger: Color = Color(0xFFE0485C),
    val contentDangerBold: Color = Color(0xFFED7685),
    val contentPositive: Color = Color(0xFF00A68C),
    val contentPositiveBold: Color = Color(0xFF5DD1BF),
    val contentNormal: Color = Color(0xFFFFFFFF),
    val contentPlaceHolder: Color = Color(0xFFA0A9BE),
    val contentDisable: Color = contentNormal.copy(alpha = 0.5f),
    val background: Color = Color(0xFF0b1426),
    val cardBackground: Color = Color(red = 1f, green = 1f, blue = 1f, alpha = 0.1f),
    val buttonEnableBackground: Color = Color(0xFF1199FA),
    val buttonDisableBackground: Color = buttonEnableBackground.copy(alpha = 0.5f),
)

val LocalBmiColor = staticCompositionLocalOf { BmiColor() }
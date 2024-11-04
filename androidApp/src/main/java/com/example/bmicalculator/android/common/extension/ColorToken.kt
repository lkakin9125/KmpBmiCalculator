package com.example.bmicalculator.android.common.extension

import androidx.compose.runtime.Composable
import com.example.bmicalculator.android.theme.LocalBmiColor
import com.example.bmicalculator.common.ColorToken

@Composable
fun ColorToken.toColor() = when (this) {
    ColorToken.Background -> LocalBmiColor.current.background
    ColorToken.ContentDanger -> LocalBmiColor.current.contentDanger
    ColorToken.ContentNormal -> LocalBmiColor.current.contentNormal
    ColorToken.ContentDangerBold -> LocalBmiColor.current.contentDangerBold
    ColorToken.ContentPositiveBold -> LocalBmiColor.current.contentPositiveBold
    ColorToken.CardBackground -> LocalBmiColor.current.cardBackground
    ColorToken.ContentPositive -> LocalBmiColor.current.contentPositive
    ColorToken.ButtonEnableBackground -> LocalBmiColor.current.buttonEnableBackground
    ColorToken.ButtonDisableBackground -> LocalBmiColor.current.buttonDisableBackground
}
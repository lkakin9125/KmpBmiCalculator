package com.example.bmicalculator.bmiCalculator.model

import com.example.bmicalculator.common.ColorToken

enum class BmiCategory(
    val displayName: String,
    val minThreshold: Double,
    val color: ColorToken,
) {
    UnderweightSevereThinness("Underweight (Severe thinness)", 0.0, ColorToken.ContentDangerBold),
    UnderweightModerateThinness("Underweight (Moderate thinness)", 16.0, ColorToken.ContentDanger),
    UnderweightMildThinness("Underweight (Mild thinness)", 17.0, ColorToken.ContentPositive),
    NormalRange("Normal range", 18.5, ColorToken.ContentPositiveBold),
    OverweightPreObese("Overweight (Pre-obese)", 25.0, ColorToken.ContentPositive),
    ObeseClassI("Obese (Class I)", 30.0, ColorToken.ContentDanger),
    ObeseClassII("Obese (Class II)", 35.0, ColorToken.ContentDanger),
    ObeseClassIII("Obese (Class III)", 40.0, ColorToken.ContentDangerBold),
}
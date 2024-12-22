package com.example.bmicalculator.bmiCalculator.bmiResult.model

import com.example.bmicalculator.common.ColorToken
import kotlin.js.JsExport


@JsExport
data class BmiResultUiState(
    val bmiResultText: String,
    val bmiResultTextColorToken: ColorToken,
    val bmiCategoryText: String,
)
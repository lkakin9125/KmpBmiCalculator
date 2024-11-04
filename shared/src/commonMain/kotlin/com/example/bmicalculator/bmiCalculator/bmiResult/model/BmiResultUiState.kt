package com.example.bmicalculator.bmiCalculator.bmiResult.model

import com.example.bmicalculator.common.ColorToken

data class BmiResultUiState(
    val bmiResultText: String,
    val bmiResultTextColorToken: ColorToken,
    val bmiCategoryText: String,
)
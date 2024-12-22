package com.example.bmicalculator.bmiCalculator.input.model

import kotlin.js.JsExport

@JsExport
data class BmiInputUiState(
    val inputText: String,
    val unitText: String,
    val placeholder: String,
    val errorText: String,
    val onInputChanged: (String) -> Unit,
)
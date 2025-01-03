package com.example.bmicalculator.hybrid

import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState

@JsExport
data class HybridUiState(
    val navigationMenuPageUiState: NavigationMenuPageUiState,
    val bmiCalculatorUiState: BmiPageUiState?,
)
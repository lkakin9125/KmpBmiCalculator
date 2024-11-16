package com.example.bmicalculator.bmiCalculator.page

import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import kotlinx.coroutines.flow.StateFlow

interface BmiCalculatorPageActor {
    val uiState: StateFlow<BmiPageUiState?>
}
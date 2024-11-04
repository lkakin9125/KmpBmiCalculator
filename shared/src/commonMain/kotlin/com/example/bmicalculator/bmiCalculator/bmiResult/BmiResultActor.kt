package com.example.bmicalculator.bmiCalculator.bmiResult

import com.example.bmicalculator.bmiCalculator.bmiResult.model.BmiResultUiState
import kotlinx.coroutines.flow.StateFlow

interface BmiResultActor {
    val uiState: StateFlow<BmiResultUiState?>
}
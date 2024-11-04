package com.example.bmicalculator.bmiCalculator.model

import com.example.bmicalculator.bmiCalculator.bmiResult.model.BmiResultUiState
import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.common.ui.model.AppButtonUiState

data class BmiPageUiState(
    val heightInputUiState: BmiInputUiState,
    val weightInputUiState: BmiInputUiState,
    val resultUiState: BmiResultUiState?,
    val buttonUiState: AppButtonUiState,
)
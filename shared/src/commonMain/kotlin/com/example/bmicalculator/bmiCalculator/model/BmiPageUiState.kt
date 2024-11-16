package com.example.bmicalculator.bmiCalculator.model

import com.example.bmicalculator.bmiCalculator.bmiResult.model.BmiResultUiState
import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.common.ui.model.AppButtonUiState

sealed interface BmiPageUiState

data object BmiPageLoadingUiState : BmiPageUiState

data class BmiPageContentUiState(
    val heightInputUiState: BmiInputUiState,
    val weightInputUiState: BmiInputUiState,
    val resultUiState: BmiResultUiState?,
    val buttonUiState: AppButtonUiState,
) : BmiPageUiState
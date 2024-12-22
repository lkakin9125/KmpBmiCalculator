package com.example.bmicalculator.bmiCalculator.model

import com.example.bmicalculator.bmiCalculator.bmiResult.model.BmiResultUiState
import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.common.ui.model.AppButtonUiState
import kotlin.js.JsExport


@JsExport
sealed interface BmiPageUiState


@JsExport
data object BmiPageLoadingUiState : BmiPageUiState


@JsExport
data class BmiPageContentUiState(
    val title:String,
    val heightInputUiState: BmiInputUiState,
    val weightInputUiState: BmiInputUiState,
    val resultUiState: BmiResultUiState?,
    val buttonUiState: AppButtonUiState,
) : BmiPageUiState
package com.example.bmicalculator.bmiCalculator.input

import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BmiHeightInputActor {
    val uiState: StateFlow<BmiInputUiState>
    val heightInMeter: StateFlow<BigDecimal?>
}
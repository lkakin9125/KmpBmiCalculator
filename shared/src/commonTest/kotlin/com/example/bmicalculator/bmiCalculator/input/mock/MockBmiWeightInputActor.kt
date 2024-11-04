package com.example.bmicalculator.bmiCalculator.input.mock

import com.example.bmicalculator.bmiCalculator.input.BmiWeightInputActor
import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.testCore.mocktComponent.MockComponent
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.coroutines.flow.MutableStateFlow

class MockBmiWeightInputActor : MockComponent, BmiWeightInputActor {
    override val weightInKg = MutableStateFlow<BigDecimal?>(null)
    override val uiState = MutableStateFlow(createDefaultUiState())

    private fun createDefaultUiState() = BmiInputUiState(
        inputText = "",
        unitText = "",
        placeholder = "",
        errorText = "",
        onInputChanged = {}
    )

    override fun setup() {
        weightInKg.tryEmit(null)
        uiState.tryEmit(createDefaultUiState())
    }
}
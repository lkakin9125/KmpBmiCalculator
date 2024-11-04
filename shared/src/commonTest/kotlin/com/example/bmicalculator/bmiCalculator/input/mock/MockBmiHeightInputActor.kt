package com.example.bmicalculator.bmiCalculator.input.mock

import com.example.bmicalculator.bmiCalculator.input.BmiHeightInputActor
import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.testCore.mocktComponent.MockComponent
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.coroutines.flow.MutableStateFlow

class MockBmiHeightInputActor : MockComponent, BmiHeightInputActor {

    override val uiState = MutableStateFlow(createDefaultUiState())
    override val heightInMeter = MutableStateFlow<BigDecimal?>(null)

    private fun createDefaultUiState() = BmiInputUiState(
        inputText = "",
        unitText = "",
        placeholder = "",
        errorText = "",
        onInputChanged = {}
    )

    override fun setup() {
        uiState.tryEmit(createDefaultUiState())
        heightInMeter.tryEmit(null)
    }
}
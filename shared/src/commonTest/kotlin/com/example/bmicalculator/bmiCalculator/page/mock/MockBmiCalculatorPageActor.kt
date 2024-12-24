package com.example.bmicalculator.bmiCalculator.page.mock

import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import com.example.bmicalculator.bmiCalculator.page.BmiCalculatorPageActor
import com.example.bmicalculator.testCore.mocktComponent.MockComponent
import kotlinx.coroutines.flow.MutableStateFlow

class MockBmiCalculatorPageActor(
    val resetUiState: BmiPageUiState? = null
) : MockComponent, BmiCalculatorPageActor {
    override fun setup() {
        uiState.tryEmit(resetUiState)
    }

    override val uiState = MutableStateFlow(resetUiState)
}
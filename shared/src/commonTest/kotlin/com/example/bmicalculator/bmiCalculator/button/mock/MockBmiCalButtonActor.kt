package com.example.bmicalculator.bmiCalculator.button.mock

import com.example.bmicalculator.bmiCalculator.button.BmiCalButtonActor
import com.example.bmicalculator.common.ui.model.AppButtonUiState
import com.example.bmicalculator.testCore.mocktComponent.MockComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class MockBmiCalButtonActor : BmiCalButtonActor, MockComponent {

    override val uiState = MutableStateFlow(createDefaultUiState())
    override val buttonClickEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    private fun createDefaultUiState() = AppButtonUiState(
        text = "Submit",
        enabled = true,
        onClick = {}
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun setup() {
        uiState.tryEmit(createDefaultUiState())
        buttonClickEvent.resetReplayCache()
    }
}
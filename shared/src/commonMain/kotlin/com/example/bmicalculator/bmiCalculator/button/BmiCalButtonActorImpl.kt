package com.example.bmicalculator.bmiCalculator.button

import com.example.bmicalculator.common.ui.model.AppButtonUiState
import com.example.bmicalculator.bmiCalculator.input.BmiHeightInputActor
import com.example.bmicalculator.bmiCalculator.input.BmiWeightInputActor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class BmiCalButtonActorImpl(
    private val scope: CoroutineScope,
    private val heightInputActor: BmiHeightInputActor,
    private val weightInputActor: BmiWeightInputActor,
) : BmiCalButtonActor {
    override val uiState = combine(heightInputActor.heightInMeter, weightInputActor.weightInKg) { height, weight ->
        when {
            height == null || weight == null -> createUiState(false)
            else -> createUiState(true)
        }
    }
        .stateIn(scope, SharingStarted.WhileSubscribed(),createUiState(false))

    override val buttonClickEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    private fun createUiState(enabled: Boolean) = AppButtonUiState(
        text = "Submit",
        enabled = enabled,
        onClick = ::onBtnClick
    )

    private fun onBtnClick() {
        buttonClickEvent.tryEmit(Unit)
    }
}
package com.example.bmicalculator.bmiCalculator.page

import com.example.bmicalculator.bmiCalculator.bmiResult.BmiResultActor
import com.example.bmicalculator.bmiCalculator.button.BmiCalButtonActor
import com.example.bmicalculator.bmiCalculator.input.BmiHeightInputActor
import com.example.bmicalculator.bmiCalculator.input.BmiWeightInputActor
import com.example.bmicalculator.bmiCalculator.model.BmiPageContentUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class BmiCalculatorPageActorImpl(
    val scope: CoroutineScope,
    val title: String,
    val heightActor: BmiHeightInputActor,
    val weightActor: BmiWeightInputActor,
    val buttonActor: BmiCalButtonActor,
    val resultActor: BmiResultActor,
) : BmiCalculatorPageActor {
    override val uiState = combine(
        heightActor.uiState,
        weightActor.uiState,
        resultActor.uiState,
        buttonActor.uiState,
    ) { heightUiState, weightUiState, resultUiState, buttonUiState ->
        BmiPageContentUiState(
            title = title,
            heightInputUiState = heightUiState,
            weightInputUiState = weightUiState,
            resultUiState = resultUiState,
            buttonUiState = buttonUiState
        )
    }
        .stateIn(scope, SharingStarted.WhileSubscribed(), null)
}
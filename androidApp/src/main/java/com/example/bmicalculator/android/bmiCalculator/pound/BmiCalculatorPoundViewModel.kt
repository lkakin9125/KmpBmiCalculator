package com.example.bmicalculator.android.bmiCalculator.pound

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.bmiCalculator.bmiResult.BmiResultActorImpl
import com.example.bmicalculator.bmiCalculator.button.BmiCalButtonActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiHeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiPoundWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class BmiCalculatorPoundViewModel : ViewModel() {
    private val heightActor by lazy { BmiHeightInputActorImpl(viewModelScope) }
    private val weightActor by lazy { BmiPoundWeightInputActorImpl(viewModelScope) }
    private val buttonActor by lazy { BmiCalButtonActorImpl(viewModelScope, heightActor, weightActor) }
    private val resultCardActor by lazy { BmiResultActorImpl(viewModelScope, heightActor, weightActor, buttonActor) }

    val uiState = combine(
        heightActor.uiState,
        weightActor.uiState,
        resultCardActor.uiState,
        buttonActor.uiState,
    ) { heightUiState, weightUiState, resultUiState, buttonUiState ->
        BmiPageUiState(
            heightUiState,
            weightUiState,
            resultUiState,
            buttonUiState
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
}
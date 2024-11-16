package com.example.bmicalculator.android.bmiCalculator.kg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.bmiCalculator.bmiResult.BmiResultActorImpl
import com.example.bmicalculator.bmiCalculator.button.BmiCalButtonActorImpl
import com.example.bmicalculator.bmiCalculator.factory.BmiCalculatorActorFactory
import com.example.bmicalculator.bmiCalculator.input.BmiHeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiKgWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class BmiCalculatorKgViewModel(
    actorFactory: BmiCalculatorActorFactory,
) : ViewModel() {
    private val pageActor = actorFactory.createPageActor(viewModelScope)
    val uiState = pageActor.uiState
}
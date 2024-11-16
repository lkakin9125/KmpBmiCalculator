package com.example.bmicalculator.bmiCalculator.input

import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BmiKgWeightInputActorImpl(
    private val scope: CoroutineScope
) : BmiWeightInputActor {
    private val weightInputFlow = MutableStateFlow("")
    override val weightInKg = weightInputFlow.map { input ->
        input.toDoubleOrNull()
            ?.takeIf { it >= 0.0 }
            ?.toBigDecimal()
    }
        .stateIn(scope, SharingStarted.WhileSubscribed(), null)

    override val uiState: StateFlow<BmiInputUiState> = weightInputFlow.map { input ->
        val inputDouble = input.toDoubleOrNull() ?: -1.0
        when {
            input.isEmpty() || inputDouble >= 0.0 -> createUiState(
                inputText = input,
            )

            else -> createUiState(
                inputText = input,
                errorText = "Invalid Input"
            )
        }
    }
        .stateIn(scope, SharingStarted.WhileSubscribed(), createUiState(inputText = ""))

    private fun createUiState(inputText: String, errorText: String = "") = BmiInputUiState(
        inputText = inputText,
        unitText = "kg",
        placeholder = "Weight in kg",
        onInputChanged = ::updateInput,
        errorText = errorText
    )

    private fun updateInput(nextInput: String) {
        weightInputFlow.tryEmit(nextInput)
    }
}
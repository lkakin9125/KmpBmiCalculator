package com.example.bmicalculator.bmiCalculator.input

import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BmiPoundWeightInputActorImpl(
    private val scope: CoroutineScope,
) : BmiWeightInputActor {
    private val weightInputFlow = MutableStateFlow("")
    override val weightInKg = weightInputFlow.map { input ->
        input.toDoubleOrNull()
            ?.takeIf { it >= 0.0 }
            ?.toBigDecimal()
            ?.divide(
                "2.2".toBigDecimal(),
                decimalMode = DecimalMode(
                    10,
                    roundingMode = RoundingMode.ROUND_HALF_FLOOR,
                    scale = 2
                )
            )
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
        unitText = "lb",
        placeholder = "Weight in lb",
        onInputChanged = ::updateInput,
        errorText = errorText
    )

    private fun updateInput(nextInput: String) {
        weightInputFlow.tryEmit(nextInput)
    }
}
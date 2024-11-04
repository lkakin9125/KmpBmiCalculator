package com.example.bmicalculator.bmiCalculator.input

import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BmiHeightInputActorImpl(
    private val scope: CoroutineScope,
) : BmiHeightInputActor {
    private val heightInputFlow = MutableStateFlow("")

    override val heightInMeter: StateFlow<BigDecimal?> = heightInputFlow.map { input ->
        input.toDoubleOrNull()
            ?.takeIf { it >= 0.0 }
            ?.toBigDecimal()
            ?.divide(100.toBigDecimal(), DecimalMode(2, RoundingMode.ROUND_HALF_FLOOR))
    }
        .stateIn(scope, SharingStarted.WhileSubscribed(), null)

    override val uiState: StateFlow<BmiInputUiState> = heightInputFlow.map { input ->
        val inputDouble = input.toDoubleOrNull() ?: -1.0
        when {
            input.isEmpty() || inputDouble >= 0.0 -> createUiState(inputText = input)

            else -> createUiState(
                inputText = input,
                errorText = "Invalid Input"
            )
        }
    }
        .stateIn(
            scope, SharingStarted.WhileSubscribed(), createUiState(inputText = "")
        )

    private fun createUiState(inputText: String, errorText: String = "") = BmiInputUiState(
        inputText = inputText,
        unitText = "cm",
        placeholder = "Height in cm",
        onInputChanged = ::updateInput,
        errorText = errorText
    )

    private fun updateInput(nextInput: String) {
        heightInputFlow.tryEmit(nextInput)
    }
}
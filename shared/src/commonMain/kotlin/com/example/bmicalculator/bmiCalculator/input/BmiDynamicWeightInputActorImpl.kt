package com.example.bmicalculator.bmiCalculator.input

import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.bmiCalculator.query.BmiInputUnitQueryActor
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn

class BmiDynamicWeightInputActorImpl(
    scope: CoroutineScope,
    val queryActor: BmiInputUnitQueryActor,
    val inputActorMap: Map<BmiInputUnit,BmiWeightInputActor>
) : BmiWeightInputActor {
    private val selectedActor = queryActor.inputUnitLce.map { lce ->
        lce.getContentOrNull()?.let { inputActorMap[it] }
    }
        .shareIn(scope, SharingStarted.WhileSubscribed(), 1)

    override val uiState: StateFlow<BmiInputUiState> = selectedActor.flatMapLatest {
        it?.uiState ?: flowOf(createDefaultUiState())
    }
        .stateIn(scope, SharingStarted.WhileSubscribed(), createDefaultUiState())

    override val weightInKg: StateFlow<BigDecimal?> = selectedActor.flatMapLatest {
        it?.weightInKg ?: flowOf(null)
    }
        .stateIn(scope, SharingStarted.WhileSubscribed(), null)

    private fun createDefaultUiState() = BmiInputUiState("", "", "", "", {})
}
package com.example.bmicalculator.bmiCalculator.query

import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.bmiCalculator.repository.BmiRepository
import com.example.bmicalculator.common.model.Lce
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.isActive

class BmiInputUnitQueryActorImpl(
    scope: CoroutineScope,
    bmiRepository: BmiRepository,
) : BmiInputUnitQueryActor {

    override val inputUnitLce: SharedFlow<Lce<Throwable, BmiInputUnit>> = flow {
        emit(Lce.Loading)
        kotlin.runCatching {
            bmiRepository.queryInputUnit()
        }
            .onFailure { if (currentCoroutineContext().isActive) emit(Lce.Failure(it)) }
            .onSuccess { if (currentCoroutineContext().isActive) emit(Lce.Content(it)) }
    }
        .shareIn(scope, SharingStarted.WhileSubscribed(), 1)
}
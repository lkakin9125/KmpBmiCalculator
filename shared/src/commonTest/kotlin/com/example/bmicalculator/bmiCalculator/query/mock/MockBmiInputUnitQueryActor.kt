package com.example.bmicalculator.bmiCalculator.query.mock

import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.bmiCalculator.query.BmiInputUnitQueryActor
import com.example.bmicalculator.common.model.Lce
import com.example.bmicalculator.testCore.mocktComponent.MockComponent
import kotlinx.coroutines.flow.MutableStateFlow

class MockBmiInputUnitQueryActor : MockComponent, BmiInputUnitQueryActor {
    override fun setup() {
        inputUnitLce.tryEmit(Lce.Loading)
    }

    override val inputUnitLce = MutableStateFlow<Lce<Throwable, BmiInputUnit>>(Lce.Loading)
}
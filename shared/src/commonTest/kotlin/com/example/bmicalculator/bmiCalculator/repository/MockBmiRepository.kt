package com.example.bmicalculator.bmiCalculator.repository

import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.testCore.mocktComponent.MockComponent
import kotlinx.coroutines.delay

class MockBmiRepository : MockComponent, BmiRepository {
    var testInputUnitFunc: suspend () -> BmiInputUnit = { BmiInputUnit.Kg }
    override fun setup() {
        testInputUnitFunc = { BmiInputUnit.Kg }
    }

    override suspend fun queryInputUnit(): BmiInputUnit {
        return testInputUnitFunc.invoke()
    }

    fun setupMockResult(
        result: BmiInputUnit,
        delayTime: Long = DEFAULT_DELAY
    ) {
        testInputUnitFunc = {
            delay(delayTime)
            result
        }
    }

    companion object{
        const val DEFAULT_DELAY = 100L
    }
}
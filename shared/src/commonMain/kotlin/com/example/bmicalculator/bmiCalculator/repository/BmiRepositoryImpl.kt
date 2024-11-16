package com.example.bmicalculator.bmiCalculator.repository

import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.common.AppDispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class BmiRepositoryImpl(
    private val dispatchers: AppDispatchers,
) : BmiRepository {
    private var numOfApiCall = 0
    // fake response due to no server setup
    override suspend fun queryInputUnit() = withContext(dispatchers.main()) {
        delay(500L)
        when ((numOfApiCall++) % 2 == 0) {
            true -> BmiInputUnit.Kg
            false -> BmiInputUnit.Pound
        }
    }
}
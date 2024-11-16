package com.example.bmicalculator.bmiCalculator.repository

import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit

interface BmiRepository {
    suspend fun queryInputUnit(): BmiInputUnit
}
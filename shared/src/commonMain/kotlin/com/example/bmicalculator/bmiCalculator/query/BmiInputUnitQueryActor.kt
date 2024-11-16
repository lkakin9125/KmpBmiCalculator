package com.example.bmicalculator.bmiCalculator.query

import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.common.model.Lce
import kotlinx.coroutines.flow.SharedFlow

interface BmiInputUnitQueryActor {
    val inputUnitLce: SharedFlow<Lce<Throwable,BmiInputUnit>>
}
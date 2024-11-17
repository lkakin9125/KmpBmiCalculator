package com.example.bmicalculator

import com.example.bmicalculator.bmiCalculator.KmpBmiCalculatorModule
import com.example.bmicalculator.common.KmpIosCommonModule
import com.example.bmicalculator.common.di.ModuleManager

class KmpIosStarter {
    fun start() {
        ModuleManager(
            listOf(
                KmpBmiCalculatorModule(),
                KmpIosCommonModule(),
            )
        )
            .startDi()
    }
}
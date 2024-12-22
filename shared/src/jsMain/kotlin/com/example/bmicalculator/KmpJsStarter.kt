package com.example.bmicalculator

import com.example.bmicalculator.bmiCalculator.KmpBmiCalculatorModule
import com.example.bmicalculator.common.KmpJsCommonModule
import com.example.bmicalculator.common.di.ModuleManager


@JsExport
class KmpJsStarter {
    fun start() {
        ModuleManager(
            listOf(
                KmpBmiCalculatorModule(),
                KmpJsCommonModule(),
            )
        )
            .startDi()
    }
}
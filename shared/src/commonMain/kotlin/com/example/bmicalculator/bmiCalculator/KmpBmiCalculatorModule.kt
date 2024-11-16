package com.example.bmicalculator.bmiCalculator

import com.example.bmicalculator.bmiCalculator.factory.BmiCalculatorActorFactory
import com.example.bmicalculator.common.di.KotlinDiModule
import org.koin.core.module.Module
import org.koin.dsl.module

class KmpBmiCalculatorModule : KotlinDiModule {
    override val koinModule: Collection<Module> = listOf(
        module {
            factory { BmiCalculatorActorFactory() }
        }
    )
}
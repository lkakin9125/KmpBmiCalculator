package com.example.bmicalculator.bmiCalculator

import com.example.bmicalculator.bmiCalculator.factory.BmiCalculatorActorFactory
import com.example.bmicalculator.bmiCalculator.repository.BmiRepository
import com.example.bmicalculator.bmiCalculator.repository.BmiRepositoryImpl
import com.example.bmicalculator.common.AppDispatchers
import com.example.bmicalculator.common.AppDispatchersImpl
import com.example.bmicalculator.common.di.KotlinDiModule
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

class KmpBmiCalculatorModule : KotlinDiModule {
    override val koinModule: Collection<Module> = listOf(
        module {
            factory { BmiCalculatorActorFactory() }
            single { BmiRepositoryImpl(get()) } bind BmiRepository::class
            factory { AppDispatchersImpl() } bind AppDispatchers::class
        }
    )
}
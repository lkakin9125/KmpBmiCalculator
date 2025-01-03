package com.example.bmicalculator.android

import com.example.bmicalculator.android.bmiCalculator.abTesting.BmiCalculatorAbTestingViewModel
import com.example.bmicalculator.android.navMenu.NavigationMenuViewModel
import com.example.bmicalculator.android.bmiCalculator.kg.BmiCalculatorKgViewModel
import com.example.bmicalculator.android.bmiCalculator.pound.BmiCalculatorPoundViewModel
import com.example.bmicalculator.common.di.KotlinDiModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class AndroidBmiCalculatorModule : KotlinDiModule {
    override val koinModule: Collection<Module> = listOf(
        module {
            viewModel { NavigationMenuViewModel() }
            viewModel { BmiCalculatorKgViewModel(get()) }
            viewModel { BmiCalculatorPoundViewModel(get()) }
            viewModel { BmiCalculatorAbTestingViewModel(get(), get()) }
        }
    )
}
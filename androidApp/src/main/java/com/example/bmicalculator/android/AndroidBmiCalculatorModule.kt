package com.example.bmicalculator.android

import com.example.bmicalculator.android.bmiCalculator.BmiCalculatorViewModel
import com.example.bmicalculator.android.navMenu.NavigationMenuViewModel
import com.example.bmicalculator.common.di.KotlinDiModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class AndroidBmiCalculatorModule : KotlinDiModule {
    override val koinModule: Collection<Module> = listOf(
        module {
            viewModel { NavigationMenuViewModel() }
            viewModel { BmiCalculatorViewModel() }
        }
    )
}
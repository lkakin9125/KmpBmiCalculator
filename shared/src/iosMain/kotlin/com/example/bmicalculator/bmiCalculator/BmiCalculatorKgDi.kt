package com.example.bmicalculator.bmiCalculator

import com.example.bmicalculator.bmiCalculator.factory.BmiCalculatorActorFactory
import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import com.example.bmicalculator.common.reactive.toKmpStream
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BmiCalculatorKgDi : KoinComponent {
    private val bmiCalActorFactory: BmiCalculatorActorFactory by inject()
    private val scope: CoroutineScope by inject()

    private val actor by lazy {
        bmiCalActorFactory.createPageActor(
            scope,
            title = NavigationMenuOption.KgBmiCalculator.displayText,
        )
    }

    fun subscribe(onNext: (BmiPageUiState?) -> Unit) = actor.uiState
        .toKmpStream(scope)
        .subscribe(onNext)
}
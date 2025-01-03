package com.example.bmicalculator.bmiCalculator

import com.example.bmicalculator.bmiCalculator.factory.BmiCalculatorActorFactory
import com.example.bmicalculator.bmiCalculator.input.BmiDynamicWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiKgWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiPoundWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.bmiCalculator.model.BmiPageLoadingUiState
import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import com.example.bmicalculator.bmiCalculator.query.BmiInputUnitQueryActorImpl
import com.example.bmicalculator.bmiCalculator.repository.BmiRepository
import com.example.bmicalculator.common.model.Lce
import com.example.bmicalculator.common.reactive.toKmpStream
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@JsExport
class BmiCalculatorAbTestingDi : KoinComponent {
    private val bmiCalActorFactory: BmiCalculatorActorFactory by inject()
    private val scope: CoroutineScope by inject()
    private val bmiRepository: BmiRepository by inject()
    private val queryActor = BmiInputUnitQueryActorImpl(scope, bmiRepository)

    private val dynamicWeightActor = BmiDynamicWeightInputActorImpl(
        scope = scope,
        queryActor = queryActor,
        inputActorMap = mapOf(
            BmiInputUnit.Kg to BmiKgWeightInputActorImpl(scope),
            BmiInputUnit.Pound to BmiPoundWeightInputActorImpl(scope),
        )
    )

    private val actor by lazy {
        bmiCalActorFactory.createPageActor(
            scope = scope,
            title = NavigationMenuOption.AbTestingBmiCalculator.displayText,
            weightActor = dynamicWeightActor
        )
    }
    val uiStateStream = combine(
        queryActor.inputUnitLce,
        actor.uiState,
    ) { lce, uiStateVal ->
        when (lce !is Lce.Content) {
            true -> BmiPageLoadingUiState
            false -> uiStateVal ?: BmiPageLoadingUiState
        }
    }
        .toKmpStream(scope)

    fun subscribe(onNext: (BmiPageUiState?) -> Unit) = uiStateStream
        .subscribe(onNext)
}
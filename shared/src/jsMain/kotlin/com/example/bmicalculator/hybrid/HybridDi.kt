package com.example.bmicalculator.hybrid

import com.example.bmicalculator.bmiCalculator.BmiCalculatorAbTestingDi
import com.example.bmicalculator.bmiCalculator.BmiCalculatorKgDi
import com.example.bmicalculator.bmiCalculator.BmiCalculatorPoundDi
import com.example.bmicalculator.bmiCalculator.factory.BmiCalculatorActorFactory
import com.example.bmicalculator.bmiCalculator.input.BmiDynamicWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiKgWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiPoundWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.bmiCalculator.query.BmiInputUnitQueryActorImpl
import com.example.bmicalculator.bmiCalculator.repository.BmiRepository
import com.example.bmicalculator.common.reactive.toFlow
import com.example.bmicalculator.common.reactive.toKmpStream
import com.example.bmicalculator.navMenu.BmiNavigationMenuDi
import com.example.bmicalculator.navMenu.NavigationMenuActorImpl
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@JsExport
class HybridDi : KoinComponent {
    private val scope: CoroutineScope by inject()
    private val kgDi = BmiCalculatorKgDi()
    private val poundDi = BmiCalculatorPoundDi()
    private val abTestingDi = BmiCalculatorAbTestingDi()
    private val menuDi = BmiNavigationMenuDi()
    private val hybridActor = HybridActor(
        scope,
        mapOf(
            NavigationMenuOption.KgBmiCalculator to kgDi.uiStateStream.toFlow(),
            NavigationMenuOption.PoundBmiCalculator to poundDi.uiStateStream.toFlow(),
            NavigationMenuOption.AbTestingBmiCalculator to abTestingDi.uiStateStream.toFlow(),
        ),
        menuUiState = menuDi.uiStateStream.toFlow(),
        menuClickEvent = menuDi.onClickEventStream.toFlow(),
    )

    val uiStateStream = hybridActor.uiState.toKmpStream(scope)

    fun subscribe(onNext: (HybridUiState?) -> Unit) = uiStateStream.subscribe(onNext)
}
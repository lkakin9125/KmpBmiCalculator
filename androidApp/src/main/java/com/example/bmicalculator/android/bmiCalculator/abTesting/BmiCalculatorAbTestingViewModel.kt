package com.example.bmicalculator.android.bmiCalculator.abTesting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.bmiCalculator.factory.BmiCalculatorActorFactory
import com.example.bmicalculator.bmiCalculator.input.BmiDynamicWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiKgWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiPoundWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.bmiCalculator.model.BmiPageLoadingUiState
import com.example.bmicalculator.bmiCalculator.query.BmiInputUnitQueryActorImpl
import com.example.bmicalculator.bmiCalculator.repository.BmiRepository
import com.example.bmicalculator.common.model.Lce
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class BmiCalculatorAbTestingViewModel(
    actorFactory: BmiCalculatorActorFactory,
    bmiRepository: BmiRepository,
) : ViewModel() {
    private val queryActor = BmiInputUnitQueryActorImpl(viewModelScope, bmiRepository)

    private val dynamicWeightActor = BmiDynamicWeightInputActorImpl(
        scope = viewModelScope,
        queryActor = queryActor,
        inputActorMap = mapOf(
            BmiInputUnit.Kg to BmiKgWeightInputActorImpl(viewModelScope),
            BmiInputUnit.Pound to BmiPoundWeightInputActorImpl(viewModelScope),
        )
    )

    private val pageActor = actorFactory.createPageActor(
        scope = viewModelScope,
        title = NavigationMenuOption.AbTestingBmiCalculator.displayText,
        weightActor = dynamicWeightActor
    )

    val uiState = combine(
        queryActor.inputUnitLce,
        pageActor.uiState,
    ) { lce, uiStateVal ->
        when (lce !is Lce.Content) {
            true -> BmiPageLoadingUiState
            false -> uiStateVal ?: BmiPageLoadingUiState
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), BmiPageLoadingUiState)
}
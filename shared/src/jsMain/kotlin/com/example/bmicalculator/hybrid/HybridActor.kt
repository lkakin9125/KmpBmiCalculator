package com.example.bmicalculator.hybrid

import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import com.example.bmicalculator.bmiCalculator.page.BmiCalculatorPageActor
import com.example.bmicalculator.navMenu.NavigationMenuActor
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class HybridActor(
    private val scope: CoroutineScope,
    private val calculatorUiStateMap: Map<NavigationMenuOption, Flow<BmiPageUiState?>>,
    private val menuUiState: Flow<NavigationMenuPageUiState>,
    private val menuClickEvent: Flow<NavigationMenuOption>,
) {
    private val selectedCalculatorUiState = menuClickEvent
        .flatMapLatest {
            println("menuClickEvent: $it")
            calculatorUiStateMap[it] ?: flowOf(null)
        }
        .stateIn(scope, SharingStarted.WhileSubscribed(), null)

    val uiState = combine(menuUiState, selectedCalculatorUiState) { menuUiState, calculatorUiState ->
        HybridUiState(menuUiState, calculatorUiState)
    }
        .stateIn(scope, SharingStarted.WhileSubscribed(), null)
}
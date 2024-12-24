package com.example.bmicalculator.hybrid

import com.example.bmicalculator.bmiCalculator.page.BmiCalculatorPageActor
import com.example.bmicalculator.navMenu.NavigationMenuActor
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import kotlinx.coroutines.flow.MutableStateFlow

class HybridActor(
    val actorMap: Map<NavigationMenuOption, BmiCalculatorPageActor>,
    val menuActor: NavigationMenuActor,
) {
    val uiState = MutableStateFlow<HybridUiState?>(null)
}
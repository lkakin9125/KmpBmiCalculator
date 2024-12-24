package com.example.bmicalculator.navMenu

import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface NavigationMenuActor {
    val uiState: StateFlow<NavigationMenuPageUiState>
    val onMenuClickEvent: SharedFlow<NavigationMenuOption>
}
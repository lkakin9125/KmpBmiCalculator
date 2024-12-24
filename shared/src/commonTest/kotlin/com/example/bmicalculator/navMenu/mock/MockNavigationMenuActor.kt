package com.example.bmicalculator.navMenu.mock

import com.example.bmicalculator.navMenu.NavigationMenuActor
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState
import com.example.bmicalculator.testCore.mocktComponent.MockComponent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class MockNavigationMenuActor(
    val resetUiState: NavigationMenuPageUiState
) : MockComponent, NavigationMenuActor {

    override val uiState = MutableStateFlow(resetUiState)
    override val onMenuClickEvent = MutableSharedFlow<NavigationMenuOption>(replay = 1)

    override fun setup() {
        uiState.tryEmit(resetUiState)
        onMenuClickEvent.resetReplayCache()
    }

}
package com.example.bmicalculator.navMenu

import com.example.bmicalculator.common.ui.model.AppFlatButtonUiState
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationMenuActorImpl(
    val scope: CoroutineScope,
): NavigationMenuActor {
    override val uiState = MutableStateFlow(
        NavigationMenuPageUiState(
            NavigationMenuOption.entries.map {
                AppFlatButtonUiState(it.displayText, true) { onMenuClickEvent.tryEmit(it) }
            }
        ))
    override val onMenuClickEvent = MutableSharedFlow<NavigationMenuOption>(extraBufferCapacity = 1)
}
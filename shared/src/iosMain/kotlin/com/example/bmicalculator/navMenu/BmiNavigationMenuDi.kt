package com.example.bmicalculator.navMenu

import com.example.bmicalculator.common.reactive.toKmpStream
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BmiNavigationMenuDi : KoinComponent {
    private val scope: CoroutineScope by inject()
    private val actor = NavigationMenuActorImpl(scope)
    private val uiState = actor.uiState
    private val onClickEvent = actor.onMenuClickEvent

    fun subscribeUiState(onNext: (NavigationMenuPageUiState?) -> Unit) = uiState
        .toKmpStream(scope)
        .subscribe(onNext)

    fun subscribeOnClickEvent(onNext: (NavigationMenuOption) -> Unit) = onClickEvent
        .toKmpStream(scope)
        .subscribe(onNext)
}

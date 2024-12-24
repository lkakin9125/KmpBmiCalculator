package com.example.bmicalculator.navMenu

import com.example.bmicalculator.common.reactive.toKmpStream
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@JsExport
class BmiNavigationMenuDi : KoinComponent {
    private val scope: CoroutineScope by inject()
    private val actor = NavigationMenuActorImpl(scope)
    private val uiState = actor.uiState
    private val onClickEvent = actor.onMenuClickEvent

    val uiStateStream = uiState
        .toKmpStream(scope)

    fun subscribeUiState(onNext: (NavigationMenuPageUiState?) -> Unit) = uiStateStream
        .subscribe(onNext)

    val onClickEventStream = onClickEvent
        .toKmpStream(scope)

    fun subscribeOnClickEvent(onNext: (NavigationMenuOption) -> Unit) = onClickEventStream
        .subscribe(onNext)
}

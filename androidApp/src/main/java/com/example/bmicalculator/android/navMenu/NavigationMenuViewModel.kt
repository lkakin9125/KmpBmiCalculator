package com.example.bmicalculator.android.navMenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.navMenu.NavigationMenuActorImpl

class NavigationMenuViewModel : ViewModel() {
    private val actor = NavigationMenuActorImpl(viewModelScope)
    val uiState = actor.uiState
    val onClickEvent = actor.onMenuClickEvent
}
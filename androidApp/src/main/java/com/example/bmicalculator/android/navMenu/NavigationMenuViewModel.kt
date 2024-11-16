package com.example.bmicalculator.android.navMenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.navMenu.NavigationMenuActor

class NavigationMenuViewModel : ViewModel() {
    private val actor = NavigationMenuActor(viewModelScope)
    val uiState = actor.uiState
    val onClickEvent = actor.onMenuClickEvent
}
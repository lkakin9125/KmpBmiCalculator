package com.example.bmicalculator.bmiCalculator.button

import com.example.bmicalculator.common.ui.model.AppButtonUiState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BmiCalButtonActor {
    val uiState: StateFlow<AppButtonUiState>
    val buttonClickEvent: SharedFlow<Unit>
}
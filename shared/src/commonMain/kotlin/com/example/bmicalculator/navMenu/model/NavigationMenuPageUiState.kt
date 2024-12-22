package com.example.bmicalculator.navMenu.model

import com.example.bmicalculator.common.ui.model.AppFlatButtonUiState
import kotlin.js.JsExport

@JsExport
data class NavigationMenuPageUiState(
    val buttons: List<AppFlatButtonUiState>,
)
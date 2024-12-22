package com.example.bmicalculator.common.ui.model

import kotlin.js.JsExport

@JsExport
data class AppFlatButtonUiState(
    val text: String,
    val enabled: Boolean,
    val onClick: () -> Unit
)
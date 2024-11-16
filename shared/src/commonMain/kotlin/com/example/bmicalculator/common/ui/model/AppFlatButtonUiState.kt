package com.example.bmicalculator.common.ui.model

data class AppFlatButtonUiState(
    val text: String,
    val enabled: Boolean,
    val onClick: () -> Unit
)
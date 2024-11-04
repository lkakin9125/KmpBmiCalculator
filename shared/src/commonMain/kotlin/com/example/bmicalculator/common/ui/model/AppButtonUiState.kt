package com.example.bmicalculator.common.ui.model

data class AppButtonUiState(
    val text: String,
    val enabled: Boolean,
    val onClick: () -> Unit
)
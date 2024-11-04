package com.example.bmicalculator.android.common.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.android.MyApplicationTheme
import com.example.bmicalculator.android.theme.LocalBmiColor
import com.example.bmicalculator.common.ui.model.AppButtonUiState

@Composable
fun BmiCalButton(uiState: AppButtonUiState) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        enabled = uiState.enabled,
        onClick = uiState.onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = LocalBmiColor.current.contentNormal,
            containerColor = LocalBmiColor.current.buttonEnableBackground,
            disabledContentColor = LocalBmiColor.current.contentDisable,
            disabledContainerColor = LocalBmiColor.current.buttonDisableBackground
        )
    ) {
        Text(text = uiState.text)
    }
}

@Preview
@Composable
private fun PreviewBmiCalButton() {
    MyApplicationTheme {
        Column(
            modifier = Modifier
                .background(LocalBmiColor.current.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf(
                AppButtonUiState("Enabled Button", true, {}),
                AppButtonUiState("Disable Button", false, {}),
            )
                .forEach { BmiCalButton(it) }
        }
    }
}
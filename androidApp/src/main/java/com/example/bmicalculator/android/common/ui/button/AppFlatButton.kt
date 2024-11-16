package com.example.bmicalculator.android.common.ui.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bmicalculator.android.theme.LocalBmiColor
import com.example.bmicalculator.common.ui.model.AppFlatButtonUiState

@Composable
fun AppFlatButton(uiState: AppFlatButtonUiState) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        enabled = uiState.enabled,
        onClick = uiState.onClick,
        colors =ButtonDefaults.textButtonColors(contentColor = LocalBmiColor.current.buttonEnableBackground)
    ) {
        Text(uiState.text)
    }
}
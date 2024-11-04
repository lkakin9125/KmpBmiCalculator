package com.example.bmicalculator.android.bmiCalculator.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.android.MyApplicationTheme
import com.example.bmicalculator.android.common.ui.container.BmiCardContainer
import com.example.bmicalculator.android.theme.LocalBmiColor
import com.example.bmicalculator.android.theme.LocalBmiTextStyle
import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState

@Composable
fun BmiInput(uiState: BmiInputUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        BmiCardContainer(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    value = uiState.inputText,
                    onValueChange = uiState.onInputChanged,
                    label = {
                        Text(
                            text = uiState.placeholder,
                            color = LocalBmiColor.current.contentPlaceHolder,
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedSupportingTextColor = LocalBmiColor.current.contentNormal,
                        unfocusedSupportingTextColor = LocalBmiColor.current.contentNormal,
                        focusedTextColor = LocalBmiColor.current.contentNormal,
                        unfocusedTextColor = LocalBmiColor.current.contentNormal,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                    ),
                )
                Text(
                    text = uiState.unitText,
                    color = LocalBmiColor.current.contentNormal,
                )
            }
        }
        Text(
            text = uiState.errorText,
            style = LocalBmiTextStyle.current.bodySmall,
            color = LocalBmiColor.current.contentDanger,
        )
    }
}


@Preview
@Composable
private fun PreviewBmiInput() {
    MyApplicationTheme {
        Column(
            modifier = Modifier
                .background(LocalBmiColor.current.background)
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf(
                BmiInputUiState("", "cm", "Height in cm", "", {}),
                BmiInputUiState("170", "cm", "Height in cm", "", {}),
                BmiInputUiState("ABC", "cm", "Height in cm", "Invalid Input", {})
            ).forEach {
                BmiInput(it)
            }
        }
    }
}
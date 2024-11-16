package com.example.bmicalculator.android.bmiCalculator.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.android.MyApplicationTheme
import com.example.bmicalculator.android.bmiCalculator.bmiResult.BmiResult
import com.example.bmicalculator.android.bmiCalculator.input.BmiInput
import com.example.bmicalculator.android.common.ui.button.BmiCalButton
import com.example.bmicalculator.android.theme.LocalBmiColor
import com.example.bmicalculator.bmiCalculator.bmiResult.model.BmiResultUiState
import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.bmiCalculator.model.BmiCategory
import com.example.bmicalculator.bmiCalculator.model.BmiPageContentUiState
import com.example.bmicalculator.bmiCalculator.model.BmiPageLoadingUiState
import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import com.example.bmicalculator.common.ui.model.AppButtonUiState

@Composable
fun BmiCalculatorPage(uiState: BmiPageUiState) {
    when (uiState) {
        BmiPageLoadingUiState -> BmiCalculatorLoadingPage()
        is BmiPageContentUiState -> BmiCalculatorContentPage(uiState)
    }
}

@Composable
fun BmiCalculatorLoadingPage() {
    Box(
        modifier = Modifier
            .background(LocalBmiColor.current.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = LocalBmiColor.current.buttonEnableBackground
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiCalculatorContentPage(uiState: BmiPageContentUiState) {
    Column(
        modifier = Modifier
            .background(LocalBmiColor.current.background)
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Bmi Calculator") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = LocalBmiColor.current.background,
                titleContentColor = LocalBmiColor.current.contentNormal,
            ),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {

            BmiInput(uiState.heightInputUiState)
            BmiInput(uiState.weightInputUiState)
        }
        uiState.resultUiState?.let { BmiResult(it) }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            BmiCalButton(uiState.buttonUiState)
        }
    }
}

@Preview
@Composable
private fun PreviewBmiCalculatorPageWithoutResult() {
    MyApplicationTheme {
        BmiCalculatorPage(
            BmiPageContentUiState(
                heightInputUiState = BmiInputUiState(
                    inputText = "200",
                    unitText = "cm",
                    placeholder = "Height in cm",
                    onInputChanged = {},
                    errorText = ""
                ),
                weightInputUiState = BmiInputUiState(
                    inputText = "50",
                    unitText = "kg",
                    placeholder = "Weight in kg",
                    onInputChanged = {},
                    errorText = ""
                ),
                resultUiState = null,
                buttonUiState = AppButtonUiState(
                    text = "Submit",
                    enabled = true,
                    onClick = {}
                ),
            )
        )
    }
}

@Preview
@Composable
private fun PreviewBmiCalculatorPageWithResult() {
    MyApplicationTheme {
        BmiCalculatorPage(
            BmiPageContentUiState(
                heightInputUiState = BmiInputUiState(
                    inputText = "200",
                    unitText = "cm",
                    placeholder = "Height in cm",
                    onInputChanged = {},
                    errorText = ""
                ),
                weightInputUiState = BmiInputUiState(
                    inputText = "50",
                    unitText = "kg",
                    placeholder = "Weight in kg",
                    onInputChanged = {},
                    errorText = ""
                ),
                resultUiState = BmiResultUiState(
                    bmiResultText = "12.5",
                    bmiCategoryText = BmiCategory.UnderweightSevereThinness.displayName,
                    bmiResultTextColorToken = BmiCategory.UnderweightSevereThinness.color,
                ),
                buttonUiState = AppButtonUiState(
                    text = "Submit",
                    enabled = true,
                    onClick = {}
                ),
            )
        )
    }
}

@Preview
@Composable
private fun PreviewBmiCalculatorPageWhenInit() {
    MyApplicationTheme {
        BmiCalculatorPage(
            BmiPageContentUiState(
                heightInputUiState = BmiInputUiState(
                    inputText = "",
                    unitText = "cm",
                    placeholder = "Height in cm",
                    onInputChanged = {},
                    errorText = ""
                ),
                weightInputUiState = BmiInputUiState(
                    inputText = "",
                    unitText = "kg",
                    placeholder = "Weight in kg",
                    onInputChanged = {},
                    errorText = ""
                ),
                resultUiState = null,
                buttonUiState = AppButtonUiState(
                    text = "Submit",
                    enabled = false,
                    onClick = {}
                ),
            )
        )
    }
}


@Preview
@Composable
private fun PreviewBmiCalculatorPageWhenLoading() {
    MyApplicationTheme {
        BmiCalculatorPage(
            BmiPageLoadingUiState
        )
    }
}
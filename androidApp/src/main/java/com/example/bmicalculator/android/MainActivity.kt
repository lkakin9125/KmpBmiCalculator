package com.example.bmicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bmicalculator.Greeting
import com.example.bmicalculator.android.bmiCalculator.BmiCalculatorViewModel
import com.example.bmicalculator.android.bmiCalculator.page.BmiCalculatorPage
import com.example.bmicalculator.bmiCalculator.model.BmiPageUiState
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                BmiCalculator()
            }
        }
    }
}

@Composable
fun BmiCalculator() {
    val viewModel = koinViewModel<BmiCalculatorViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    uiState?.let { BmiCalculatorPage(it) }
}
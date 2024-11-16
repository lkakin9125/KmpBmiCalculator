package com.example.bmicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bmicalculator.android.bmiCalculator.kg.BmiCalculatorKgViewModel
import com.example.bmicalculator.android.bmiCalculator.page.BmiCalculatorPage
import com.example.bmicalculator.android.navMenu.NavigationMenuPage
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.android.bmiCalculator.pound.BmiCalculatorPoundViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "menu") {
                    composable("menu") { NavigationMenuPage { navController.navigate(route = it) } }
                    composable(NavigationMenuOption.KgBmiCalculator.navPath) {
                        BmiCalculatorInKg()
                    }
                    composable(NavigationMenuOption.PoundBmiCalculator.navPath) {
                        BmiCalculatorInPound()
                    }
                }
            }
        }
    }
}

@Composable
fun BmiCalculatorInKg() {
    val viewModel = koinViewModel<BmiCalculatorKgViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    uiState?.let { BmiCalculatorPage(it) }
}

@Composable
fun BmiCalculatorInPound() {
    val viewModel = koinViewModel<BmiCalculatorPoundViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    uiState?.let { BmiCalculatorPage(it) }
}
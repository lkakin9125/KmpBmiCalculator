package com.example.bmicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bmicalculator.android.bmiCalculator.BmiCalculatorViewModel
import com.example.bmicalculator.android.bmiCalculator.page.BmiCalculatorPage
import com.example.bmicalculator.android.navMenu.NavigationMenuPage
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
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
                        BmiCalculator()
                    }
                    composable(NavigationMenuOption.PoundBmiCalculator.navPath) {
                        Text("PoundBmiCalculator")
                    }
                }
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
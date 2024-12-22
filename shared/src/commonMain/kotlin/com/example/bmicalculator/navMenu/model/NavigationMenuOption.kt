package com.example.bmicalculator.navMenu.model

import kotlin.js.JsExport

@JsExport
enum class NavigationMenuOption(val displayText: String, val navPath: String) {
    KgBmiCalculator("BmiCalculator (kg)", "bmi/kg"),
    PoundBmiCalculator("BmiCalculator (lb)", "bmi/pound"),
    AbTestingBmiCalculator("BmiCalculator (AbTesting)", "bmi/ab_testing")
}
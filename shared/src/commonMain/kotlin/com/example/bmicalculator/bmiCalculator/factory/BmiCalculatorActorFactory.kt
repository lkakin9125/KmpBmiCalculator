package com.example.bmicalculator.bmiCalculator.factory

import com.example.bmicalculator.bmiCalculator.bmiResult.BmiResultActor
import com.example.bmicalculator.bmiCalculator.bmiResult.BmiResultActorImpl
import com.example.bmicalculator.bmiCalculator.button.BmiCalButtonActor
import com.example.bmicalculator.bmiCalculator.button.BmiCalButtonActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiHeightInputActor
import com.example.bmicalculator.bmiCalculator.input.BmiHeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiKgWeightInputActorImpl
import com.example.bmicalculator.bmiCalculator.input.BmiWeightInputActor
import com.example.bmicalculator.bmiCalculator.page.BmiCalculatorPageActor
import com.example.bmicalculator.bmiCalculator.page.BmiCalculatorPageActorImpl
import kotlinx.coroutines.CoroutineScope

class BmiCalculatorActorFactory {
    fun createPageActor(
        scope: CoroutineScope,
        heightActor: BmiHeightInputActor = BmiHeightInputActorImpl(scope),
        weightActor: BmiWeightInputActor = BmiKgWeightInputActorImpl(scope),
        buttonActor: BmiCalButtonActor = BmiCalButtonActorImpl(scope, heightActor, weightActor),
        resultActor: BmiResultActor = BmiResultActorImpl(scope, heightActor, weightActor, buttonActor),
    ): BmiCalculatorPageActor = BmiCalculatorPageActorImpl(
        scope = scope,
        heightActor = heightActor,
        weightActor = weightActor,
        buttonActor = buttonActor,
        resultActor = resultActor,
    )
}
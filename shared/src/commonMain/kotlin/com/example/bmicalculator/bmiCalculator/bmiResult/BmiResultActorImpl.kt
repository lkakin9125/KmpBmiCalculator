package com.example.bmicalculator.bmiCalculator.bmiResult

import com.example.bmicalculator.bmiCalculator.bmiResult.model.BmiResultUiState
import com.example.bmicalculator.bmiCalculator.button.BmiCalButtonActor
import com.example.bmicalculator.bmiCalculator.input.BmiHeightInputActor
import com.example.bmicalculator.bmiCalculator.input.BmiWeightInputActor
import com.example.bmicalculator.bmiCalculator.model.BmiCategory
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class BmiResultActorImpl(
    private val scope: CoroutineScope,
    private val bmiHeightInputActor: BmiHeightInputActor,
    private val bmiWeightInputActor: BmiWeightInputActor,
    private val bmiCalButtonActor: BmiCalButtonActor,
) : BmiResultActor {

    private val sortedBmiCategory = BmiCategory.entries.sortedByDescending { it.minThreshold }

    private val bmi = bmiCalButtonActor.buttonClickEvent.mapLatest {
        combine(
            bmiHeightInputActor.heightInMeter,
            bmiWeightInputActor.weightInKg,
        ) { height, weight ->
            weight ?: return@combine null
            height ?: return@combine null

            weight.divide(
                other = height * height,
                DecimalMode(3, roundingMode = RoundingMode.FLOOR)
            )
        }
            .first()
    }

    private val bmiAndCategory = bmi.map { bmiVal ->
        bmiVal ?: return@map null
        val category = sortedBmiCategory.find { it.minThreshold.toBigDecimal() <= bmiVal } ?: return@map null
        println("bmi: $bmiVal, category: $category")
        bmiVal to category
    }

    override val uiState = bmiAndCategory.map { data ->
        val (bmiVal, categoryVal) = data ?: return@map null
        BmiResultUiState(
            bmiResultText = bmiVal.toPlainString(),
            bmiCategoryText = categoryVal.displayName,
            bmiResultTextColorToken = categoryVal.color,
        )
    }
        .stateIn(scope, SharingStarted.WhileSubscribed(), null)
}
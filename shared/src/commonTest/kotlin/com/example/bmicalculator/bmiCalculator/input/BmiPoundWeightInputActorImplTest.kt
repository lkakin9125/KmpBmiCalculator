package com.example.bmicalculator.bmiCalculator.input

import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.testCore.base.BaseKmpTest
import com.example.bmicalculator.testCore.coroutine.TestDispatcherPlugin
import com.example.bmicalculator.testCore.coroutine.runTestWithPlugin
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlin.test.Test
import kotlin.test.assertEquals

class BmiPoundWeightInputActorImplTest : BaseKmpTest() {
    private val mockOnInputChange = { _: String -> }

    override fun initPlugins() = listOf(
        TestDispatcherPlugin()
    )

    private fun createActor(scope: CoroutineScope) = BmiPoundWeightInputActorImpl(scope)

    @Test
    fun test_uiState_AND_weightInKg_WHEN_input_is_negative_num() = testUiStateAndWeight(
        "-66",
        expectedHeight = null,
        expectedUiState = BmiInputUiState(
            inputText = "-66",
            unitText = "lb",
            placeholder = "Weight in lb",
            onInputChanged = mockOnInputChange,
            errorText = "Invalid Input"
        )
    )

    @Test
    fun test_uiState_AND_weightInKg_WHEN_input_is_66() = testUiStateAndWeight(
        "66",
        expectedHeight = 30.toBigDecimal(),
        expectedUiState = BmiInputUiState(
            inputText = "66",
            unitText = "lb",
            placeholder = "Weight in lb",
            onInputChanged = mockOnInputChange,
            errorText = ""
        )
    )

    @Test
    fun test_uiState_AND_weightInKg_WHEN_input_is_50() = testUiStateAndWeight(
        "50",
        expectedHeight = "22.73".toBigDecimal(),
        expectedUiState = BmiInputUiState(
            inputText = "50",
            unitText = "lb",
            placeholder = "Weight in lb",
            onInputChanged = mockOnInputChange,
            errorText = ""
        )
    )

    @Test
    fun test_uiState_AND_weightInKg_WHEN_input_is_NAN() = testUiStateAndWeight(
        "not a number",
        expectedHeight = null,
        expectedUiState = BmiInputUiState(
            inputText = "not a number",
            unitText = "lb",
            placeholder = "Weight in lb",
            onInputChanged = mockOnInputChange,
            errorText = "Invalid Input"
        )
    )

    @Test
    fun test_uiState_AND_weightInKg_WHEN_input_is_empty_string() = testUiStateAndWeight(
        "",
        expectedHeight = null,
        expectedUiState = BmiInputUiState(
            inputText = "",
            unitText = "lb",
            placeholder = "Weight in lb",
            onInputChanged = mockOnInputChange,
            errorText = ""
        )
    )

    private fun testUiStateAndWeight(
        input: String,
        expectedHeight: BigDecimal?,
        expectedUiState: BmiInputUiState
    ) = runTestWithPlugin {
        val actor = createActor(backgroundScope)

        val job = merge(
            actor.uiState,
            actor.weightInKg
        ).launchIn(backgroundScope)

        actor.uiState.value.onInputChanged(input)
        assertEquals(expectedUiState, actor.uiState.value.toTest())
        assertEquals(expectedHeight, actor.weightInKg.value)

        job.cancel()
    }

    private fun BmiInputUiState.toTest() = this.copy(onInputChanged = mockOnInputChange)

}
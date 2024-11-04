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

class BmiHeightInputActorImplTest : BaseKmpTest() {
    override fun initPlugins() = listOf(TestDispatcherPlugin())
    private fun createActor(scope: CoroutineScope) = BmiHeightInputActorImpl(scope)

    private val mockOnChange: (String) -> Unit = { _ -> }

    @Test
    fun test_uiState_AND_heightInMeter_WHEN_input_is_negative_num() = testUIStateAndHeight(
        "-170",
        expectedHeight = null,
        expectedUiState = BmiInputUiState(
            inputText = "-170",
            unitText = "cm",
            placeholder = "Height in cm",
            onInputChanged = mockOnChange,
            errorText = "Invalid Input"
        )
    )

    @Test
    fun test_uiState_AND_heightInMeter_WHEN_input_is_170() = testUIStateAndHeight(
        "170",
        expectedHeight = "1.7".toBigDecimal(),
        expectedUiState = BmiInputUiState(
            inputText = "170",
            unitText = "cm",
            placeholder = "Height in cm",
            onInputChanged = mockOnChange,
            errorText = ""
        )
    )

    @Test
    fun test_uiState_AND_heightInMeter_WHEN_input_is_NAN() = testUIStateAndHeight(
        "not a number",
        expectedHeight = null,
        expectedUiState = BmiInputUiState(
            inputText = "not a number",
            unitText = "cm",
            placeholder = "Height in cm",
            onInputChanged = mockOnChange,
            errorText = "Invalid Input"
        )
    )

    @Test
    fun test_uiState_AND_heightInMeter_WHEN_input_is_empty_string() = testUIStateAndHeight(
        "",
        expectedHeight = null,
        expectedUiState = BmiInputUiState(
            inputText = "",
            unitText = "cm",
            placeholder = "Height in cm",
            onInputChanged = mockOnChange,
            errorText = ""
        )
    )

    private fun testUIStateAndHeight(
        input: String,
        expectedHeight: BigDecimal?,
        expectedUiState: BmiInputUiState
    ) = runTestWithPlugin {
        val actor = createActor(backgroundScope)

        val job = merge(
            actor.uiState,
            actor.heightInMeter
        ).launchIn(backgroundScope)

        actor.uiState.value.onInputChanged(input)
        assertEquals(expectedUiState, actor.uiState.value.toTest())
        assertEquals(expectedHeight, actor.heightInMeter.value)

        job.cancel()
    }

    private fun BmiInputUiState.toTest() = this.copy(onInputChanged = mockOnChange)

}
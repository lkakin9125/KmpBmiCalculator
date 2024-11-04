package com.example.bmicalculator.bmiCalculator.button

import com.example.bmicalculator.common.ui.model.AppButtonUiState
import com.example.bmicalculator.bmiCalculator.input.mock.MockBmiHeightInputActor
import com.example.bmicalculator.bmiCalculator.input.mock.MockBmiWeightInputActor
import com.example.bmicalculator.testCore.base.BaseKmpTest
import com.example.bmicalculator.testCore.coroutine.TestDispatcherPlugin
import com.example.bmicalculator.testCore.coroutine.runTestWithPlugin
import com.example.bmicalculator.testCore.mocktComponent.MockComponentResetPlugin
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlin.test.Test
import kotlin.test.assertEquals

class BmiCalButtonActorImplTest : BaseKmpTest() {
    private val mockOnClick = {}

    private val mockHeightInputActor by lazy {
        MockBmiHeightInputActor()
    }
    private val mockWeightInputActor by lazy {
        MockBmiWeightInputActor()
    }

    override fun initPlugins() = listOf(
        TestDispatcherPlugin(),
        MockComponentResetPlugin(
            listOf(
                mockHeightInputActor,
                mockWeightInputActor,
            )
        )
    )

    private fun createActor(scope: CoroutineScope) = BmiCalButtonActorImpl(
        scope = scope,
        heightInputActor = mockHeightInputActor,
        weightInputActor = mockWeightInputActor,
    )

    @Test
    fun test_uiState_WHEN_height_is_170_AND_weight_is_50() = testUiState(
        height = 170.toBigDecimal(),
        weight = 50.toBigDecimal(),
        expectedUiState = AppButtonUiState(
            text = "Submit",
            enabled = true,
            onClick = mockOnClick
        )
    )

    @Test
    fun test_uiState_WHEN_height_is_170_AND_weight_is_null() = testUiState(
        height = 170.toBigDecimal(),
        weight = null,
        expectedUiState = AppButtonUiState(
            text = "Submit",
            enabled = false,
            onClick = mockOnClick
        )
    )

    @Test
    fun test_uiState_WHEN_height_is_null_AND_weight_is_null() = testUiState(
        height = null,
        weight = null,
        expectedUiState = AppButtonUiState(
            text = "Submit",
            enabled = false,
            onClick = mockOnClick
        )
    )

    @Test
    fun test_uiState_GIVEN_height_is_170_AND_weight_is_50_WHEN_height_is_null_AND_weight_is_50() =
        testUiState(
            height = null,
            weight = 50.toBigDecimal(),
            expectedUiState = AppButtonUiState(
                text = "Submit",
                enabled = false,
                onClick = mockOnClick
            )
        )

    @Test
    fun test_buttonClickEvent_WHEN_uiState_onClick_is_called() = runTestWithPlugin {
        mockHeightInputActor.heightInMeter.tryEmit(170.toBigDecimal())
        mockWeightInputActor.weightInKg.tryEmit(50.toBigDecimal())

        val actor = createActor(backgroundScope)
        val testEventFlow = actor.buttonClickEvent.stateIn(
            backgroundScope,
            SharingStarted.WhileSubscribed(),
            null
        )
        val job = merge(testEventFlow, actor.uiState).launchIn(backgroundScope)

        assertEquals(
            expected = null,
            actual = testEventFlow.value
        )

        actor.uiState.value.onClick()

        assertEquals(
            expected = Unit,
            actual = testEventFlow.value
        )

        job.cancel()
    }

    private fun testUiState(
        height: BigDecimal?,
        weight: BigDecimal?,
        expectedUiState: AppButtonUiState
    ) = runTestWithPlugin {
        val actor = createActor(backgroundScope)
        val job = actor.uiState.launchIn(backgroundScope)

        mockHeightInputActor.heightInMeter.tryEmit(height)
        mockWeightInputActor.weightInKg.tryEmit(weight)

        assertEquals(
            expected = expectedUiState,
            actual = actor.uiState.value.toTest()
        )

        job.cancel()
    }

    private fun AppButtonUiState.toTest() = copy(onClick = mockOnClick)
}
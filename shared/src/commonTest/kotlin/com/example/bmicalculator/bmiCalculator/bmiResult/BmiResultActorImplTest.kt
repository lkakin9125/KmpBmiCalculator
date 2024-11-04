package com.example.bmicalculator.bmiCalculator.bmiResult

import com.example.bmicalculator.bmiCalculator.bmiResult.model.BmiResultUiState
import com.example.bmicalculator.bmiCalculator.button.mock.MockBmiCalButtonActor
import com.example.bmicalculator.bmiCalculator.input.mock.MockBmiHeightInputActor
import com.example.bmicalculator.bmiCalculator.input.mock.MockBmiWeightInputActor
import com.example.bmicalculator.bmiCalculator.model.BmiCategory
import com.example.bmicalculator.testCore.base.BaseKmpTest
import com.example.bmicalculator.testCore.coroutine.TestDispatcherPlugin
import com.example.bmicalculator.testCore.coroutine.runTestWithPlugin
import com.example.bmicalculator.testCore.mocktComponent.MockComponentResetPlugin
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlin.test.Test
import kotlin.test.assertEquals

class BmiResultActorImplTest : BaseKmpTest() {
    private val mockHeightInputActor by lazy {
        MockBmiHeightInputActor()
    }
    private val mockWeightInputActor by lazy {
        MockBmiWeightInputActor()
    }
    private val mockBmiCalButtonActor by lazy {
        MockBmiCalButtonActor()
    }

    private fun createActor(
        scope: CoroutineScope,
    ) = BmiResultActorImpl(
        scope = scope,
        bmiHeightInputActor = mockHeightInputActor,
        bmiWeightInputActor = mockWeightInputActor,
        bmiCalButtonActor = mockBmiCalButtonActor,
    )

    override fun initPlugins() = listOf(
        TestDispatcherPlugin(),
        MockComponentResetPlugin(
            listOf(
                mockHeightInputActor,
                mockWeightInputActor,
                mockBmiCalButtonActor,
            )
        )
    )

    @Test
    fun test_uiState_GIVEN_button_clicked_GIVEN_button_clicked_WHEN_height_is_2m_AND_weight_is_50kg() = testUiStateGivenButtonClicked(
        weight = 50.toBigDecimal(),
        height = 2.toBigDecimal(),
        expectedUiState = BmiResultUiState(
            bmiResultText = "12.5",
            bmiCategoryText = BmiCategory.UnderweightSevereThinness.displayName,
            bmiResultTextColorToken = BmiCategory.UnderweightSevereThinness.color,
        ),
    )

    @Test
    fun test_uiState_GIVEN_button_clicked_GIVEN_button_clicked_WHEN_height_is_1m_AND_weight_is_50kg() = testUiStateGivenButtonClicked(
        weight = 50.toBigDecimal(),
        height = 1.toBigDecimal(),
        expectedUiState = BmiResultUiState(
            bmiResultText = "50",
            bmiCategoryText = BmiCategory.ObeseClassIII.displayName,
            bmiResultTextColorToken = BmiCategory.ObeseClassIII.color,
        ),
    )

    @Test
    fun test_uiState_GIVEN_button_clicked_GIVEN_button_clicked_WHEN_height_is_null_AND_weight_is_null() = testUiStateGivenButtonClicked(
        weight = null,
        height = null,
        expectedUiState = null,
    )

    @Test
    fun test_uiState_GIVEN_button_clicked_GIVEN_button_clicked_WHEN_height_is_null_AND_weight_is_50kg() = testUiStateGivenButtonClicked(
        weight = null,
        height = 50.toBigDecimal(),
        expectedUiState = null,
    )

    @Test
    fun test_uiState_GIVEN_button_clicked_GIVEN_button_clicked_WHEN_height_is_2m_AND_weight_is_null() = testUiStateGivenButtonClicked(
        weight = 2.toBigDecimal(),
        height = null,
        expectedUiState = null,
    )

    private fun testUiStateGivenButtonClicked(
        height: BigDecimal?,
        weight: BigDecimal?,
        expectedUiState: BmiResultUiState?,
    ) = runTestWithPlugin {

        val actor = createActor(backgroundScope)
        val job = actor.uiState.launchIn(backgroundScope)

        mockHeightInputActor.heightInMeter.tryEmit(height)
        mockWeightInputActor.weightInKg.tryEmit(weight)

        assertEquals(null, actor.uiState.value)

        mockBmiCalButtonActor.buttonClickEvent.tryEmit(Unit)

        assertEquals(expectedUiState, actor.uiState.value)

        job.cancel()
    }
}
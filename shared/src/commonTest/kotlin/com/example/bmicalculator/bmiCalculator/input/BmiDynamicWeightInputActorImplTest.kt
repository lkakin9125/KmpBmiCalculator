package com.example.bmicalculator.bmiCalculator.input

import com.example.bmicalculator.bmiCalculator.input.mock.MockBmiWeightInputActor
import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.bmiCalculator.query.mock.MockBmiInputUnitQueryActor
import com.example.bmicalculator.common.model.Lce
import com.example.bmicalculator.testCore.base.BaseKmpTest
import com.example.bmicalculator.testCore.coroutine.TestDispatcherPlugin
import com.example.bmicalculator.testCore.coroutine.runTestWithPlugin
import com.example.bmicalculator.testCore.mocktComponent.MockComponentResetPlugin
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlin.test.Test
import kotlin.test.assertEquals

class BmiDynamicWeightInputActorImplTest : BaseKmpTest() {
    private val mockOnInputChange = { _: String -> }
    private val mockKgActor = MockBmiWeightInputActor()
    private val mockPoundActor = MockBmiWeightInputActor()
    private val mockQueryActor = MockBmiInputUnitQueryActor()
    override fun initPlugins() = listOf(
        TestDispatcherPlugin(),
        MockComponentResetPlugin(
            listOf(
                mockKgActor,
                mockPoundActor,
                mockQueryActor,
            )
        )
    )

    private fun createActor(scope: CoroutineScope) = BmiDynamicWeightInputActorImpl(
        scope,
        queryActor = mockQueryActor,
        inputActorMap = mapOf(
            BmiInputUnit.Kg to mockKgActor,
            BmiInputUnit.Pound to mockPoundActor
        ),
    )

    @Test
    fun test_weight_AND_uiState_WHEN_inputUnit_is_KG() = testWeightAndUiState(
        inputUnitLce = Lce.Content(BmiInputUnit.Kg),
        kgWeight = genKgWeight(),
        kgUiState = genKgUiState(),
        poundWeight = genPoundWeight(),
        poundUiState = genPoundUiState(),
        expectedWeight = genKgWeight(),
        expectedUiState = genKgUiState(),
    )

    @Test
    fun test_weight_AND_uiState_WHEN_inputUnit_is_Pound() = testWeightAndUiState(
        inputUnitLce = Lce.Content(BmiInputUnit.Pound),
        kgWeight = genKgWeight(),
        kgUiState = genKgUiState(),
        poundWeight = genPoundWeight(),
        poundUiState = genPoundUiState(),
        expectedWeight = genPoundWeight(),
        expectedUiState = genPoundUiState(),
    )

    @Test
    fun test_weight_AND_uiState_WHEN_inputUnit_is_Loading() = testWeightAndUiState(
        inputUnitLce = Lce.Loading,
        kgWeight = genKgWeight(),
        kgUiState = genKgUiState(),
        poundWeight = genPoundWeight(),
        poundUiState = genPoundUiState(),
        expectedWeight = null,
        expectedUiState = BmiInputUiState("", "","", "", mockOnInputChange),
    )

    private fun genKgWeight() = 50.toBigDecimal()
    private fun genKgUiState() = BmiInputUiState(
        "50", "kg", "input", "", mockOnInputChange
    )

    private fun genPoundWeight() = "45.45".toBigDecimal()
    private fun genPoundUiState() = BmiInputUiState(
        "100", "lb", "input", "", mockOnInputChange
    )

    private fun testWeightAndUiState(
        inputUnitLce: Lce<Throwable, BmiInputUnit>,
        kgWeight: BigDecimal?,
        kgUiState: BmiInputUiState,
        poundWeight: BigDecimal?,
        poundUiState: BmiInputUiState,
        expectedWeight: BigDecimal?,
        expectedUiState: BmiInputUiState,
    ) = runTestWithPlugin {
        val actor = createActor(backgroundScope)
        val job = merge(actor.uiState, actor.weightInKg).launchIn(backgroundScope)

        mockQueryActor.inputUnitLce.tryEmit(inputUnitLce)
        mockKgActor.weightInKg.tryEmit(kgWeight)
        mockKgActor.uiState.tryEmit(kgUiState)
        mockPoundActor.weightInKg.tryEmit(poundWeight)
        mockPoundActor.uiState.tryEmit(poundUiState)

        assertEquals(
            expected = expectedUiState,
            actual = actor.uiState.value.copy(onInputChanged = mockOnInputChange)
        )

        assertEquals(
            expected = expectedWeight,
            actual = actor.weightInKg.value
        )

        job.cancel()
    }
}
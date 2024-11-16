package com.example.bmicalculator.bmiCalculator.query

import com.example.bmicalculator.bmiCalculator.model.BmiInputUnit
import com.example.bmicalculator.bmiCalculator.repository.MockBmiRepository
import com.example.bmicalculator.common.model.Lce
import com.example.bmicalculator.testCore.base.BaseKmpTest
import com.example.bmicalculator.testCore.coroutine.TestDispatcherPlugin
import com.example.bmicalculator.testCore.coroutine.runTestWithPlugin
import com.example.bmicalculator.testCore.mocktComponent.MockComponentResetPlugin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.advanceTimeBy
import kotlin.test.Test
import kotlin.test.assertEquals

class BmiInputUnitQueryActorImplTest : BaseKmpTest() {
    private val mockBmiRepository = MockBmiRepository()
    override fun initPlugins() = listOf(
        TestDispatcherPlugin(),
        MockComponentResetPlugin(
            listOf(mockBmiRepository)
        )
    )

    private fun createActor(scope: CoroutineScope) = BmiInputUnitQueryActorImpl(
        scope,
        mockBmiRepository
    )

    @Test
    fun test_inputUnitLce_WHEN_repo_returns_KG() = testInputUnitLce(
        mockInputUnitResult = BmiInputUnit.Kg,
        expectedBeforeDelayLce = Lce.Loading,
        expectedAfterDelayLce = Lce.Content(BmiInputUnit.Kg),
    )

    @Test
    fun test_inputUnitLce_WHEN_repo_returns_POUND() = testInputUnitLce(
        mockInputUnitResult = BmiInputUnit.Pound,
        expectedBeforeDelayLce = Lce.Loading,
        expectedAfterDelayLce = Lce.Content(BmiInputUnit.Pound),
    )

    private fun testInputUnitLce(
        mockInputUnitResult: BmiInputUnit,
        expectedBeforeDelayLce: Lce<Throwable, BmiInputUnit>?,
        expectedAfterDelayLce: Lce<Throwable, BmiInputUnit>?,
    ) = runTestWithPlugin {
        mockBmiRepository.setupMockResult(mockInputUnitResult)
        val actor = createActor(backgroundScope)

        val testLceFlow = actor.inputUnitLce
            .stateIn(backgroundScope, SharingStarted.WhileSubscribed(), null)
        val job = testLceFlow.launchIn(backgroundScope)

        assertEquals(
            expected = expectedBeforeDelayLce,
            actual = testLceFlow.value
        )

        advanceTimeBy(MockBmiRepository.DEFAULT_DELAY + 1)

        assertEquals(
            expected = expectedAfterDelayLce,
            actual = testLceFlow.value
        )

        job.cancel()
    }
}
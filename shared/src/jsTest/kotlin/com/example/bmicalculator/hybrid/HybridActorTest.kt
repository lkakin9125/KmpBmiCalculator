package com.example.bmicalculator.hybrid

import com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState
import com.example.bmicalculator.bmiCalculator.model.BmiPageContentUiState
import com.example.bmicalculator.bmiCalculator.model.BmiPageLoadingUiState
import com.example.bmicalculator.bmiCalculator.page.mock.MockBmiCalculatorPageActor
import com.example.bmicalculator.common.ui.model.AppButtonUiState
import com.example.bmicalculator.common.ui.model.AppFlatButtonUiState
import com.example.bmicalculator.navMenu.mock.MockNavigationMenuActor
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState
import com.example.bmicalculator.testCore.base.BaseKmpTest
import com.example.bmicalculator.testCore.base.KmpTestPlugin
import com.example.bmicalculator.testCore.coroutine.TestDispatcherPlugin
import com.example.bmicalculator.testCore.coroutine.runTestWithPlugin
import com.example.bmicalculator.testCore.mocktComponent.MockComponentResetPlugin
import kotlinx.coroutines.flow.launchIn
import kotlin.test.Test
import kotlin.test.assertEquals

class HybridActorTest
    : BaseKmpTest() {
    private val mockOnClick = {}
    private val mockOnTextChange = { _: String -> }

    private val mockKgActor by lazy { MockBmiCalculatorPageActor(genMockUiState("kg")) }
    private val mockPoundActor by lazy { MockBmiCalculatorPageActor(genMockUiState("lb")) }
    private val mockAbTestingActor by lazy { MockBmiCalculatorPageActor(BmiPageLoadingUiState) }
    private val mockNavMenuActor by lazy { MockNavigationMenuActor(genNavMenuUiState()) }

        val testDispatcherPlugin = TestDispatcherPlugin()
    override fun initPlugins(): List<KmpTestPlugin> {
        return listOf(
            testDispatcherPlugin,
            MockComponentResetPlugin(
                listOf(
                    mockKgActor,
                    mockPoundActor,
                    mockAbTestingActor,
                )
            )
        )
    }

    private fun createActor() = HybridActor(
        actorMap = mapOf(
            NavigationMenuOption.AbTestingBmiCalculator to mockAbTestingActor,
        ),
        menuActor = mockNavMenuActor
    )

    @Test
    fun test_uiState_WHEN_nothing_selected() = testUiState(
        selectedNavOption = null,
        expectedUiState = HybridUiState(
            navigationMenuPageUiState = genNavMenuUiState(),
            bmiCalculatorUiState = null,
        )
    )

    @Test
    fun test_uiState_WHEN_selected_kg() = testUiState(
        selectedNavOption = NavigationMenuOption.KgBmiCalculator,
        expectedUiState = HybridUiState(
            navigationMenuPageUiState = genNavMenuUiState(),
            bmiCalculatorUiState = mockKgActor.resetUiState,
        )
    )

    @Test
    fun test_uiState_WHEN_selected_pound() = testUiState(
        selectedNavOption = NavigationMenuOption.PoundBmiCalculator,
        expectedUiState = HybridUiState(
            navigationMenuPageUiState = genNavMenuUiState(),
            bmiCalculatorUiState = mockPoundActor.resetUiState,
        )
    )

    @Test
    fun test_uiState_WHEN_selected_ab_testing() = testUiState(
        selectedNavOption = NavigationMenuOption.AbTestingBmiCalculator,
        expectedUiState = HybridUiState(
            navigationMenuPageUiState = genNavMenuUiState(),
            bmiCalculatorUiState = mockAbTestingActor.resetUiState,
        )
    )

    private fun testUiState(
        selectedNavOption: NavigationMenuOption?,
        expectedUiState: HybridUiState?,
    ) = runTestWithPlugin {
        throw Throwable()
        val actor = createActor()
        val job = actor.uiState.launchIn(backgroundScope)

        if (selectedNavOption != null) mockNavMenuActor.onMenuClickEvent.tryEmit(selectedNavOption)
        println("hello myDebug, expectedUiState: $expectedUiState, actor.uiState.value: ${actor.uiState.value}")

        assertEquals(
            expected = expectedUiState,
            actual = actor.uiState.value
        )

        job.cancel()
    }

    private fun genNavMenuUiState() = NavigationMenuPageUiState(
        listOf(
            AppFlatButtonUiState("kg", true, mockOnClick),
            AppFlatButtonUiState("lb", true, mockOnClick),
            AppFlatButtonUiState("ab_testing", true, mockOnClick),
        )
    )

    private fun genMockUiState(title: String) = BmiPageContentUiState(
        title = title,
        heightInputUiState = BmiInputUiState("", "", "", "", mockOnTextChange),
        weightInputUiState = BmiInputUiState("", "", "", "", mockOnTextChange),
        resultUiState = null,
        buttonUiState = AppButtonUiState("Submit", false, mockOnClick),
    )
}
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlin.test.Test
import kotlin.test.assertEquals

class HybridActorTest
    : BaseKmpTest() {
    private val mockOnClick = {}
    private val mockOnTextChange = { _: String -> }

    private val mockKgUiState by lazy { MutableStateFlow(genMockUiState("kg")) }
    private val mockPoundUiState by lazy { MutableStateFlow(genMockUiState("lb")) }
    private val mockAbTestingUiState by lazy { MutableStateFlow(BmiPageLoadingUiState) }
    private val mockMenuUiState by lazy { MutableStateFlow(genNavMenuUiState())}
    private val mockMenuEvent by lazy { MutableSharedFlow<NavigationMenuOption>(replay = 1) }

    override fun initPlugins(): List<KmpTestPlugin> {
        return listOf(
            TestDispatcherPlugin()
        )
    }

    private fun createActor(scope: CoroutineScope) = HybridActor(
        scope = scope,
        calculatorUiStateMap = mapOf(
            NavigationMenuOption.KgBmiCalculator to mockKgUiState,
            NavigationMenuOption.PoundBmiCalculator to mockPoundUiState,
            NavigationMenuOption.AbTestingBmiCalculator to mockAbTestingUiState,
        ),
        menuUiState = mockMenuUiState,
        menuClickEvent = mockMenuEvent
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
            bmiCalculatorUiState = mockKgUiState.value,
        )
    )

    @Test
    fun test_uiState_WHEN_selected_pound() = testUiState(
        selectedNavOption = NavigationMenuOption.PoundBmiCalculator,
        expectedUiState = HybridUiState(
            navigationMenuPageUiState = genNavMenuUiState(),
            bmiCalculatorUiState = mockPoundUiState.value,
        )
    )

    @Test
    fun test_uiState_WHEN_selected_ab_testing() = testUiState(
        selectedNavOption = NavigationMenuOption.AbTestingBmiCalculator,
        expectedUiState = HybridUiState(
            navigationMenuPageUiState = genNavMenuUiState(),
            bmiCalculatorUiState = mockAbTestingUiState.value,
        )
    )

    private fun testUiState(
        selectedNavOption: NavigationMenuOption?,
        expectedUiState: HybridUiState?,
    ) = runTestWithPlugin {
        val actor = createActor(backgroundScope)
        val job = actor.uiState.launchIn(backgroundScope)

        if (selectedNavOption != null) mockMenuEvent.tryEmit(selectedNavOption)
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
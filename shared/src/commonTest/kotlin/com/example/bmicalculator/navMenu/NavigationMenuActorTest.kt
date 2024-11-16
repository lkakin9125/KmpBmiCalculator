package com.example.bmicalculator.navMenu

import com.example.bmicalculator.common.ui.model.AppFlatButtonUiState
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState
import com.example.bmicalculator.testCore.base.BaseKmpTest
import com.example.bmicalculator.testCore.coroutine.TestDispatcherPlugin
import com.example.bmicalculator.testCore.coroutine.runTestWithPlugin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import kotlin.test.Test
import kotlin.test.assertEquals

class NavigationMenuActorTest : BaseKmpTest() {
    private val mockOnClick = {}

    override fun initPlugins() = listOf(
        TestDispatcherPlugin(),
    )

    private fun createActor(scope: CoroutineScope) = NavigationMenuActor(
        scope = scope,
    )

    @Test
    fun test_uiState() = runTestWithPlugin {
        val actor = createActor(backgroundScope)
        val job = actor.uiState.launchIn(backgroundScope)

        assertEquals(
            expected = NavigationMenuPageUiState(
                listOf(
                    AppFlatButtonUiState(
                        text = NavigationMenuOption.KgBmiCalculator.displayText,
                        enabled = true,
                        onClick = mockOnClick,
                    ),
                    AppFlatButtonUiState(
                        text = NavigationMenuOption.PoundBmiCalculator.displayText,
                        enabled = true,
                        onClick = mockOnClick,
                    )
                )
            ),
            actual = actor.uiState.value.toTest(),
        )

        job.cancel()
    }

    @Test
    fun test_click_result_WHEN_click_on_index_0() = testOnClick(
        buttonIndex = 0,
        NavigationMenuOption.KgBmiCalculator,
    )

    @Test
    fun test_click_result_WHEN_click_on_index_1() = testOnClick(
        buttonIndex = 1,
        NavigationMenuOption.PoundBmiCalculator,
    )

    private fun testOnClick(
        buttonIndex: Int,
        expectedOption: NavigationMenuOption,
    ) = runTestWithPlugin {
        val actor = createActor(backgroundScope)
        val testOnClickFlow = actor.onMenuClickEvent
            .stateIn(backgroundScope, SharingStarted.WhileSubscribed(), null)
        val job = merge(testOnClickFlow, actor.uiState).launchIn(backgroundScope)

        actor.uiState.value.buttons[buttonIndex].onClick()
        assertEquals(
            expected = expectedOption,
            actual = testOnClickFlow.value
        )

        job.cancel()
    }

    private fun NavigationMenuPageUiState.toTest() = copy(
        this.buttons.map { it.copy(onClick = mockOnClick) }
    )
}
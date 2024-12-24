package com.example.bmicalculator.testCore.coroutine

import com.example.bmicalculator.testCore.base.BaseKmpTest
import com.example.bmicalculator.testCore.base.KmpTestPlugin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

class TestDispatcherPlugin : KmpTestPlugin {
    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher by lazy { UnconfinedTestDispatcher(TestCoroutineScheduler()) }
    val fakeAppDispatcher by lazy { FakeAppDispatchers(testDispatcher) }
    override fun beforeEach(testRef: BaseKmpTest) {

    }

    override fun afterEach(testRef: BaseKmpTest) {
        testDispatcher[Job]?.children?.forEach { it.cancel() }
    }
}

fun BaseKmpTest.runTestWithPlugin(testBody: suspend TestScope.() -> Unit): TestResult {
    val plugin = this.plugins.find { it is TestDispatcherPlugin } as? TestDispatcherPlugin
        ?: throw IllegalStateException("TestDispatcherPlugin not included")
    return runTest(plugin.testDispatcher, testBody = testBody)
}
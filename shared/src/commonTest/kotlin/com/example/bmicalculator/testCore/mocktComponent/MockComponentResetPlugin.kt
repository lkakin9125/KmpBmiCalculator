package com.example.bmicalculator.testCore.mocktComponent

import com.example.bmicalculator.testCore.base.BaseKmpTest
import com.example.bmicalculator.testCore.base.KmpTestPlugin

class MockComponentResetPlugin(
    private val mockComponents: List<MockComponent>
) : KmpTestPlugin {
    override fun beforeEach(testRef: BaseKmpTest) {
        mockComponents.forEach { it.setup() }
    }

    override fun afterEach(testRef: BaseKmpTest) {

    }
}
package com.example.bmicalculator.testCore.base

interface KmpTestPlugin {
    fun beforeEach(testRef: BaseKmpTest)
    fun afterEach(testRef: BaseKmpTest)
}
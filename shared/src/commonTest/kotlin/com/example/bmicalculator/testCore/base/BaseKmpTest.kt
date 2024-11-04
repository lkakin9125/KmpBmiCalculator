package com.example.bmicalculator.testCore.base

import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseKmpTest {
    abstract fun initPlugins(): List<KmpTestPlugin>
    val plugins by lazy {
        initPlugins()
    }

    @BeforeTest
    fun beforeEach() {
        plugins.forEach { it.beforeEach(this) }
    }

    @AfterTest
    fun afterEach() {
        plugins.forEach { it.afterEach(this) }
    }
}
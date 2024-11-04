package com.example.bmicalculator.testCore.coroutine

import com.example.bmicalculator.common.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher

class FakeAppDispatchers(private val dispatcher: CoroutineDispatcher) : AppDispatchers {
    override fun default() = dispatcher
    override fun io() = dispatcher
    override fun main() = dispatcher
}
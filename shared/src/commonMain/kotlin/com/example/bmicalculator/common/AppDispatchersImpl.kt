package com.example.bmicalculator.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppDispatchersImpl : AppDispatchers {
    override fun default() = Dispatchers.Default
    override fun io() = getIoDispatcher()
    override fun main() = Dispatchers.Main
}

expect fun getIoDispatcher(): CoroutineDispatcher
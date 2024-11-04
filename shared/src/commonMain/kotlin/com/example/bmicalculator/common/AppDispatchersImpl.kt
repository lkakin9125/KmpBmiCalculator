package com.example.bmicalculator.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class AppDispatchersImpl : AppDispatchers {
    override fun default() = Dispatchers.Default
    override fun io() = Dispatchers.IO
    override fun main() = Dispatchers.Main
}
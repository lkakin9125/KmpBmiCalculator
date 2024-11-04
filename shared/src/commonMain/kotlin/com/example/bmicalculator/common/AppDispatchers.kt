package com.example.bmicalculator.common

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    fun default(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}
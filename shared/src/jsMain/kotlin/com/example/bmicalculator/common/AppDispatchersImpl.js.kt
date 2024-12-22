package com.example.bmicalculator.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun getIoDispatcher(): CoroutineDispatcher {
    return Dispatchers.Default
}
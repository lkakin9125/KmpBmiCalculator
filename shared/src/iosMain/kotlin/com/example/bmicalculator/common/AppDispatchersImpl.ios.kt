package com.example.bmicalculator.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual fun getIoDispatcher(): CoroutineDispatcher {
    return Dispatchers.IO
}
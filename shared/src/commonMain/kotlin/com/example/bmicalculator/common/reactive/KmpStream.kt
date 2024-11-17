package com.example.bmicalculator.common.reactive

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class KmpStream<T>(
    val scope: CoroutineScope,
    val flow: Flow<T>,
) {
    fun subscribe(onNext: (T) -> Unit): () -> Unit {
        val job = flow.onEach {
            onNext(it)
        }
            .launchIn(scope)
        return { job.cancel() }
    }
}

fun <T> Flow<T>.toKmpStream(scope: CoroutineScope): KmpStream<T> = KmpStream(scope, this)
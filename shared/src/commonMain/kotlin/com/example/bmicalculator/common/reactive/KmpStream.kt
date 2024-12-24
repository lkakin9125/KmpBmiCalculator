package com.example.bmicalculator.common.reactive

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.js.JsExport

@JsExport
interface KmpStream<T> {
    fun subscribe(onNext: (T) -> Unit): () -> Unit
}

class KmpStreamFlow<T>(
    private val scope: CoroutineScope,
    val flow: Flow<T>,
) : KmpStream<T> {
    override fun subscribe(onNext: (T) -> Unit): () -> Unit {
        val job = flow.onEach {
            onNext(it)
        }
            .launchIn(scope)
        return { job.cancel() }
    }
}

fun <T> Flow<T>.toKmpStream(scope: CoroutineScope): KmpStream<T> = KmpStreamFlow(scope, this)
fun <T> KmpStream<T>.toFlow() = when(this){
    is KmpStreamFlow -> this.flow
    else -> channelFlow {
        val cancel = subscribe {trySend(it) }
        awaitClose { cancel() }
    }
}
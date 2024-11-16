package com.example.bmicalculator.common.model

sealed interface Lce<out Error, out Value> {
    data object Loading : Lce<Nothing, Nothing>
    data class Content<T>(val value: T) : Lce<Nothing, T>
    data class Failure<E>(val error: E) : Lce<E, Nothing>

    fun getContentOrNull(): Value? {
        return when (this) {
            is Loading,
            is Failure -> null

            is Content -> value
        }
    }
}
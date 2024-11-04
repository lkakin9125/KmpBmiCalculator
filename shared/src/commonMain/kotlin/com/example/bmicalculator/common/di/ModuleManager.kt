package com.example.bmicalculator.common.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class ModuleManager(
    private val modules: List<KotlinDiModule>,
    private val customStartDi: (KoinApplication.() -> Unit)? = null
) {
    private val allKmpModule by lazy {
        modules.distinct()
    }

    fun startDi() {
        startKoin {
            customStartDi?.invoke(this)
            modules(modules.flatMap { it.koinModule })
        }
    }
}
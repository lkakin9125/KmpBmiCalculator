package com.example.bmicalculator.common

import com.example.bmicalculator.common.di.KotlinDiModule
import kotlinx.coroutines.MainScope
import org.koin.core.module.Module
import org.koin.dsl.module

class KmpJsCommonModule: KotlinDiModule {
    override val koinModule: Collection<Module> = listOf(
        module {
            factory { MainScope() }
        }
    )
}
package com.example.bmicalculator.android

import android.app.Application
import com.example.bmicalculator.common.di.ModuleManager
import org.koin.android.ext.koin.androidContext

class BmiCalculatorApplication : Application() {
    private val moduleManager by lazy {
        ModuleManager(
            modules = listOf(
                AndroidBmiCalculatorModule()
            ),
            customStartDi = {
                androidContext(this@BmiCalculatorApplication)
            }
        )
    }

    override fun onCreate() {
        super.onCreate()
        moduleManager.startDi()
    }
}
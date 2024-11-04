package com.example.bmicalculator.extension

actual fun String.kmpFormat(vararg args: String) = this.format(args = args)
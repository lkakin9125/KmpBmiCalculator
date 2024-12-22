package com.example.bmicalculator

actual fun getPlatform(): Platform {
    return JsPlatform()
}

private class JsPlatform : Platform {
    override val name: String get() = "JS"
}
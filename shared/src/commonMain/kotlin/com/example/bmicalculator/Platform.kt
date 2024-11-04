package com.example.bmicalculator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
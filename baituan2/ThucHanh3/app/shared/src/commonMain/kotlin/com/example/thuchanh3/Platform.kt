package com.example.thuchanh3

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
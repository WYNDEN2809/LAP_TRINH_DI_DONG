package com.example.day1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
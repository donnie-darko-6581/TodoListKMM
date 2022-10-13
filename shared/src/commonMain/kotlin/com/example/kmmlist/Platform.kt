package com.example.kmmlist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
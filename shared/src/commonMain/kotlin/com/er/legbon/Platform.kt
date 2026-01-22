package com.er.legbon

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
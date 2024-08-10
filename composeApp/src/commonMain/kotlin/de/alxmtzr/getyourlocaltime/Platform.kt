package de.alxmtzr.getyourlocaltime

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
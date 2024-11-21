package com.random.util

import kotlin.math.pow

fun getResourceAsText(path: String): String? =
        object {}.javaClass.getResource(path)?.readText()

fun Int.pow(power: Int): Int {
    return this.toDouble().pow(power).toInt()
}
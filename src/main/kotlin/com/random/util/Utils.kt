package com.random.util

import kotlin.math.pow

fun getResourceAsText(path: String): String? =
        object {}.javaClass.getResource(path)?.readText()

fun Int.pow(power: Int): Int {
    return this.toDouble().pow(power).toInt()
}

fun Char.intValue(): Int {
    return this.code - 'a'.code
}

fun Array<Int>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun <T> shortFormatArray(array: Array<T>): String {
    return "[ ${array[0]}, ${array[1]}, ${array[2]}, ${array[3]}, ${array[4]}, ... ] (${array.size} elements)"
}
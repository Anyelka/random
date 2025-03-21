package com.random.util

import kotlin.math.pow

fun getResourceAsText(path: String): String? =
        object {}.javaClass.getResource(path)?.readText()

fun Int.pow(power: Int): Int {
    return this.toDouble().pow(power).toInt()
}

fun Int.sqrt(): Int {
    return kotlin.math.sqrt(this.toDouble()).toInt()
}

fun Char.intValue(): Int {
    return this.code - 'a'.code
}

fun Array<Int>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun CharArray.swap(i: Int, j: Int) {
    this[i] = this[j].also { this[j] = this[i] }
}

fun CharArray.reverse(start: Int, end: Int) {
    var i = start
    var j = end - 1
    while (i < j) {
        this[i] = this[j].also { this[j] = this[i] }
        i++
        j--
    }
}

fun <T> shortFormatArray(array: Array<T>) = if(array.size > 5) shortFormat(array) else array.contentToString()

private fun <T> shortFormat(array: Array<T>) =
    "[ ${array[0]}, ${array[1]}, ${array[2]}, ${array[3]}, ${array[4]}, ... ] (${array.size} elements)"

fun isCorrectStringWithExpected(result: Any, expected: Any) = isCorrectString(result, expected) + if(result != expected) " - should be $expected" else ""

fun isCorrectString(result: Any, expected: Any) = if(result == expected) "Correct" else "WRONG RESULT !!!!!!"
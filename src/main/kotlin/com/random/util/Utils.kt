package com.random.util

import kotlin.math.pow

fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()

fun Int.pow(power: Int) = this.toDouble().pow(power).toInt()

fun Int.sqrt() = kotlin.math.sqrt(this.toDouble()).toInt()

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

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> = first + other.first to second + other.second

fun Pair<Int, Int>.isIn(board: Array<CharArray>) =
    first < board.size && second < board[0].size && first >= 0 && second >= 0

fun <T> shortFormatArrayIfNeeded(array: Array<T>) =
    if (array.size > 25) shortFormatArray(array) else array.contentToString()

fun shortFormatArrayIfNeeded(array: IntArray) =
    if (array.size > 25) shortFormatIntArray(array) else array.contentToString()

private fun <T> shortFormatArray(array: Array<T>) =
    "[ ${array[0]}, ${array[1]}, ${array[2]}, ${array[3]}, ${array[4]}, ... ] (${array.size} elements)"

private fun shortFormatIntArray(array: IntArray) =
    "[ ${array[0]}, ${array[1]}, ${array[2]}, ${array[3]}, ${array[4]}, ... ] (${array.size} elements)"

fun isCorrectStringWithExpected(result: Any, expected: Any): String {
    val areEqual = areEqual(result, expected)
    return isCorrectString(areEqual) + expectedString(areEqual, expected)
}

fun <T> isCorrectStringWithExpectedWithSet(result: List<T>, expected: List<T>): String  {
    val areEqual = areSetEqual(result, expected)
    return isCorrectString(areEqual) + expectedString(areEqual, expected)
}

fun <T> isCorrectStringWithExpectedWithSetOfSets(result: List<List<T>>, expected: List<List<T>>): String  {
    val areEqual = areSetOfSetsEqual(result, expected)
    return isCorrectString(areEqual) + expectedString(areEqual, expected)
}

fun isCorrectStringWithExpectedFromCollection(result: Any, expected: Array<IntArray>): String {
    val isContained = isContained(result, expected)
    return isCorrectString(isContained) + expectedStringFromArrayOfIntArrays(isContained, expected)
}

fun isCorrectString(areEqual: Boolean) = if (areEqual) "Correct" else "WRONG RESULT !!!!!!"
private fun expectedString(areEqual: Boolean, expected: Any) = if (!areEqual) " - should be ${format(expected)}" else ""
private fun expectedStringFromArrayOfIntArrays(areEqual: Boolean, expected: Array<IntArray>)
    = if (!areEqual) " - should be any of ${formatArrayOfIntArrays(expected)}" else ""

fun Pair<Any, Any>.test(method: (Any) -> Any) {
    val result = method { first }
    println("Result for ${format(first)} is: ${format(result)} - ${isCorrectStringWithExpected(result, second)}")
}

fun Pair<Array<IntArray>, Any>.testArrayOfIntArrays(method: (Any) -> Any) {
    val result = method { first }
    println("Result for ${formatArrayOfIntArrays(first)} is: ${format(result)} - ${isCorrectStringWithExpected(result, second)}")
}

fun <T> Pair<Any, List<T>>.testWithSet(method: (Any) -> List<T>) {
    val result = method { first }
    println("Result for ${format(first)} is: ${format(result)} - ${isCorrectStringWithExpectedWithSet(result, second)}")
}

fun <T> Pair<Any, List<List<T>>>.testWithSetOfSets(method: (Any) -> List<List<T>>) {
    val result = method { first }
    println("Result for ${format(first)} is: ${format(result)} - ${isCorrectStringWithExpectedWithSetOfSets(result, second)}")
}


fun Pair<Any, Any>.testWithoutPrintInput(method: (Any) -> Any) {
    val result = method { first }
    println("Result is: ${format(result)} - ${isCorrectStringWithExpected(result, second)}")
}

private fun format(value: Any): String =
    when (value) {
        is Array<*> -> shortFormatArrayIfNeeded(value)
        is IntArray -> shortFormatArrayIfNeeded(value.toTypedArray())
        is Pair<*, *> -> (format(value.first!!) to format(value.second!!)).toString()
        else -> value.toString()
    }

fun formatArrayOfIntArrays(array: Array<IntArray>): String {
    return shortFormatArrayIfNeeded(array.map { shortFormatArrayIfNeeded(it) }.toTypedArray())
}

fun areEqual(obj1: Any?, obj2: Any?): Boolean {
    return when {
        obj1 is Array<*> && obj2 is Array<*> -> obj1.contentEquals(obj2)
        obj1 is IntArray && obj2 is IntArray -> obj1.contentEquals(obj2)
        obj1 is Int && obj2 is Int -> obj1 == obj2
        else -> obj1 == obj2
    }
}

private fun <T> areSetEqual(list1: List<T>, list2: List<T>) = list1.toSet() == list2.toSet()

private fun <T> areSetOfSetsEqual(list1: List<List<T>>, list2: List<List<T>>)
    = list1.map { it.toSet() }.toSet() == list2.map { it.toSet() }.toSet()

private fun isContained(obj: Any, expected: Array<IntArray>): Boolean {
    return isContained(obj, expected.toList())
}

private fun isContained(obj: Any, expected: Collection<Any>): Boolean {
    return expected.any { areEqual(obj, it) }
}

fun Int.toDigits(): List<Int> = toString().map { it.toString().toInt() }
fun Long.toDigits(): List<Int> = toString().map { it.toString().toInt() }
fun Int.absoluteValue() = if (this < 0) -this else this
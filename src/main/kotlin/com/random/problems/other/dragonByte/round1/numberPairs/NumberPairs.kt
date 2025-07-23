package com.random.problems.other.dragonByte.round1.numberPairs

import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Paths

const val directory = "/other/dragonByte/round1/numberPairs"

fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()

fun main() {
    //listOf("N1", "N2").forEach { test(it) }
    solve("N3")
}

private fun test(filename: String) {
    val result = getInputTestCases(filename).map { solve(it) }
    val expectedResult = getOutputTestCaseResults("${filename}_expected")
    println("Result for: $filename is: ${isCorrect(result, expectedResult)}")
}

private fun isCorrect(result: List<String>, expected: List<String>): String {
    return if(result == expected) "CORRECT" else "wrong... - first wrong answer: ${firstWrong(result, expected)}"
}

private fun firstWrong(result: List<String>, expected: List<String>): String {
    return result.withIndex().first { (index, it) -> it != expected[index] }
        .let { (index, pair) -> "line: $index is $pair - it should be ${expected[index]}" }
}

private fun solve(filename: String) {
    val result = getInputTestCases(filename).map { solve(it) }
    Files.writeString(Paths.get("${filename}.out"), result.joinToString(separator = "\n"))
}

private fun getInputTestCases(filename: String) =
    getResourceAsText("$directory/$filename.in")!!.lines().drop(1).filter { it.isNotEmpty() }.map { it.toLong() }

private fun getOutputTestCaseResults(filename: String) =
    getResourceAsText("$directory/$filename.out")!!.lines().filter { it.isNotEmpty() }
fun solve(d: Long): String {
    return Solution().solve(d)
}

fun solveBigInteger(longD: Long): String {
    if(longD.digitsSum() % 9 != 0) return "NONE"
    val d = BigInteger.valueOf(longD)
    var a = d

    var aDigitSum = longD.digitsSum()
    var lowDigitSum = 0
    var iterations = 0

    while(aDigitSum != lowDigitSum) {
        val lastDigit = (a % BigInteger.TEN).toInt()
        a = (a / BigInteger.TEN) + BigInteger.ONE
        aDigitSum = aDigitSum - lastDigit + 1
        lowDigitSum += (10 - lastDigit)
        iterations++
    }

    val result = a * BigInteger.TEN.pow(iterations)
    return "${result - d} $result"
}

fun Long.digitsSum(): Int {
    var sum = 0
    var num = this
    while(num > 0) {
        sum += (num % 10L).toInt()
        num /= 10
    }
    return sum
}

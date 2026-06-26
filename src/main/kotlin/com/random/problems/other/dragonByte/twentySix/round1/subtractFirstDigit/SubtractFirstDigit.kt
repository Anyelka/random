package com.random.problems.other.dragonByte.twentySix.round1.subtractFirstDigit

import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Paths

const val directory = "/other/dragonByte/twentySix/round1/subtractFirstDigit"

fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()

fun main() {
    listOf("S0_1", "S1", "S0_2", "S2").forEach { test(it) }
    solve("S00")
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
    getResourceAsText("${directory}/$filename.in")!!.lines().drop(1).filter { it.isNotEmpty() }.map { it.toBigInteger() }

private fun getOutputTestCaseResults(filename: String) =
    getResourceAsText("$directory/$filename.out")!!.lines().filter { it.isNotEmpty() }

fun solve(d: BigInteger): String {
    return Solution1().solve(d)
}
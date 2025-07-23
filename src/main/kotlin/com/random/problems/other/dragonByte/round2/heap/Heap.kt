package com.random.problems.other.dragonByte.round2.heap

import com.random.problems.other.dragonByte.round1.numberPairs.Solution
import java.nio.file.Files
import java.nio.file.Paths

const val directory = "/other/dragonByte/round2/heap"

fun main() {
    test("H0")
    test("H1_0")
    test("H1_2") { result -> result.expandTo(149) }
    solve("H1")
}

fun getResourceAsText(path: String): String? = object {}.javaClass.getResource(path)?.readText()
private fun getInputTestCases(filename: String) = getResourceAsText("${directory}/$filename.in")!!
        .lines().drop(1).filter { it.isNotEmpty() }.map { it.toInt() }

private fun test(filename: String) {
    test(filename) { result -> result }
}

private fun test(filename: String, expand: (MutableList<Int>) -> List<Int>) {
    val result = expand(getInputTestCases(filename).map { solve(it) }.toMutableList())
    val expectedResult = getOutputTestCaseResults("${filename}_expected")
    println("Result for: $filename is: ${isCorrect(result, expectedResult)}")
}

private fun MutableList<Int>.expandTo(max: Int): List<Int> {
    for(i in this.size..max) {
        this.add(-1)
    }
    return this
}

private fun getOutputTestCaseResults(filename: String) =
    getResourceAsText("${directory}/$filename.out")!!.lines().filter { it.isNotEmpty() }.map { it.toInt() }
fun solve(d: Long): String {
    return Solution().solve(d)
}

private fun isCorrect(result: List<Int>, expected: List<Int>): String {
    return if(result == expected) "CORRECT" else "wrong... - first wrong answer: ${firstWrong(result, expected)}"
}

private fun firstWrong(result: List<Int>, expected: List<Int>): String {
    return result.withIndex().first { (index, it) -> it != expected[index] }
        .let { (index, pair) -> "line: ${index + 1} is $pair - it should be ${expected[index]}" }
}

private fun solve(filename: String) {
    val result = getInputTestCases(filename).map { solve(it) }
    Files.writeString(Paths.get("${filename}.out"), result.joinToString(separator = "\n"))
}

private fun solve(number: Int): Int {
    //val indices = Array(number + 1) { mutableSetOf<Int>() }
    val placeCount = Array(number + 1) { 0 }

    //val parentNodeCount = Array(number + 1) { 0 }
    //val childNodeCount = Array(number + 1) { 0 }

    fun countChildrenAndParents(num: Int, level: Int): Int {
        if(num > number) return -1
        //parentNodeCount[num] = level
        val currentChildNodeCount = (1 + countChildrenAndParents(num * 2, level + 1)) + (1 + countChildrenAndParents(num * 2 + 1, level + 1))
        //childNodeCount[num] = currentChildNodeCount

        val parentNodeCount = level

        val range = currentChildNodeCount+1..<(number - parentNodeCount + 1)
        for(n in range) {
            placeCount[n] += 1
        }

        return currentChildNodeCount
    }

    countChildrenAndParents(1, 0)

    return placeCount.withIndex().sumOf { it.index * it.value }
}

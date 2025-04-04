package com.random.problems.hackerrank.queensAttack

import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import com.random.util.getResourceAsText
import com.random.util.isCorrectString

val FILE_PATH = "/hackerrank/queensAttack/TestCase"

fun main() {
    val testCases = listOf(
        "0",
        "01",
        "02",
        "2",
        "19"
    )

    testCases.forEach { it.test() }

}

private fun String.test() {
    val inputFile = getResourceAsText("$FILE_PATH${this}_in")
    val outputFile = getResourceAsText("$FILE_PATH${this}_out")

    val lines: List<String> = inputFile!!.lines()
    val n = lines.first().split(" ")[0].toInt()
    val queenPosition: Pair<Int, Int> = Pair(lines[1].split(" ")[0].toInt(), lines[1].split(" ")[1].toInt())
    val obstacles: Array<Array<Int>> = lines.takeLast(lines.size - 2).map { line ->
        line.split(" ").map { it.toInt() }.toTypedArray()
    }.toTypedArray()

    runAndLogTime() {
        val result = Solution4().queensAttack(n, obstacles.size, queenPosition.first, queenPosition.second, obstacles)
        val expectedResult = outputFile!!.lines()[0].toInt()
        println("The queen can attack $result spaces - ${isCorrectString(result, expectedResult)}")
    }
}

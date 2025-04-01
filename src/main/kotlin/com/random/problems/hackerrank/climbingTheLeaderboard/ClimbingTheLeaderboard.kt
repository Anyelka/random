package com.random.problems.hackerrank.climbingTheLeaderboard

import com.random.util.isCorrectString
import com.random.util.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/hackerrank/climbingTheLeaderboard/TestCase"

fun main() {

    val testCases = arrayOf(0, 1, 6)

    testCases.forEach { test(it) }
}

fun test(testCase: Int) {
    val input = getResourceAsText("${FILE_PATH}${testCase}_in")
    val ranked = input!!.lines()[1].split(" ").map { it.toInt() }.toTypedArray()
    val player = input.lines()[3].split(" ").map { it.toInt() }.toTypedArray()

    val output = getResourceAsText("${FILE_PATH}${testCase}_out")
    val expectedResults = output!!.lines().map { it.toInt() }.toTypedArray()

    val time = measureTime {
        println("TestCase$testCase:")
        val result = Solution3().climbingLeaderboard(ranked, player)

        // 1. log result of each line 1-by-1:
        result.withIndex().forEach { (i, result) ->
            if(i < 10) println("Rank of ${i+1}. player with score: ${player[i]} is: $result. - ${isCorrectString(result, expectedResults[i])}")
            if(result != expectedResults[i]) {
                println("debug")
            }
        }

        // 2. only log total result:
        if(result.withIndex().all { (i, result) -> result == expectedResults[i] }) {
            println("Correct solution")
        } else {
            println("WRONG SOLUTION!!!!!")
        }
    }
    println("Time taken: $time")
}

package com.random.problems.hackerrank.snakesAndLadders

import com.random.util.getResourceAsText

const val FILE_PATH = "/hackerrank/snakesAndLadders/TestCase"

fun main() {
    val testCases = intArrayOf(0, 1, 3)
    testCases.forEach { it.test() }
}

private fun Int.test() {
    println("TestCase$this:")
    val inputLines = getResourceAsText("$FILE_PATH${this}_in")!!.lines().drop(1)
    val outputLines = getResourceAsText("$FILE_PATH${this}_out")!!.lines()

    val games = toSnakesAndLaddersGames(inputLines)

    games.withIndex().forEach { (i, game) ->
        val result = Solution3().quickestWayUp(game.first, game.second)
        println("Result for ${i + 1}. game: $result - ${correct(result, outputLines[i])}")
    }

}

fun correct(result: Int, expected: String): String =
    if (result == expected.toInt()) "Correct" else "WRONG SOLUTION !!!!"

fun toSnakesAndLaddersGames(inputLines: List<String>): List<Pair<Array<Array<Int>>, Array<Array<Int>>>> {
    var isLadder = false
    var ladders = mutableListOf<Array<Int>>()
    var snakes = mutableListOf<Array<Int>>()
    val games = mutableListOf<Pair<Array<Array<Int>>, Array<Array<Int>>>>()
    for (line in inputLines) {
        if (line.toIntOrNull() != null) {
            if (!isLadder && ladders.isNotEmpty() && snakes.isNotEmpty()) {
                games.add(ladders.toTypedArray() to snakes.toTypedArray())
                ladders = mutableListOf()
                snakes = mutableListOf()
            }
            isLadder = !isLadder
        } else {
            line.split(" ").map { it.toInt() }.toTypedArray().let {
                if (isLadder) ladders.add(it) else snakes.add(it)
            }
        }
    }
    games.add(ladders.toTypedArray() to snakes.toTypedArray())
    return games
}
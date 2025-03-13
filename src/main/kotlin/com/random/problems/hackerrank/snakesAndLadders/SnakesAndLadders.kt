package com.random.problems.hackerrank.snakesAndLadders

import com.random.util.getResourceAsText

const val FILE_PATH = "/hackerrank/snakesAndLadders/TestCase"

fun main() {
    val testCases = intArrayOf(0, 1)
    testCases.forEach { it.test() }
}

private fun Int.test() {
    println("TestCase$this:")
    val inputLines = getResourceAsText("$FILE_PATH${this}_in")!!.lines().drop(1)
    val outputLines = getResourceAsText("$FILE_PATH${this}_out")!!.lines()

    val games = toSnakesAndLaddersGames(inputLines)

    games.withIndex().forEach { (i, game) ->
        val result = quickestWayUp(game.first, game.second)
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

fun quickestWayUp(ladders: Array<Array<Int>>, snakes: Array<Array<Int>>): Int {
    return quickestWayUp(ladders.toMap(), snakes.toMap())
}

fun Array<Array<Int>>.toMap() = this.associate { it[0] to it[1] }

// Breadth First Search solution
//      start from square 1 with 0 required steps and go through the elements of the queue
//          we take the element from queue and store each square in a map with the value of the required minimum steps
//          go through each possible dice roll and calculate the next square
//              - add the dice roll value to the current square
//              - take the snakes and ladders into account - slide up / down
//              - do not go over 100
//          calculate the required steps for the next square by adding 1 to the required steps of the current square
//          load the next square with the required steps into the queue
//      after all elements are processed we can look up from the map how many steps are under key 100
//
//      Time complexity: O(n * m)       = O(100 * 6)
//      Space complexity: O(n)          = O(100)
//
fun quickestWayUp(ladders: Map<Int, Int>, snakes: Map<Int, Int>): Int {
    val dp = mutableMapOf<Int, Int>()
    val queue = ArrayDeque<Pair<Int, Int>>().also { it.add(1 to 0) }
    val visited = mutableSetOf<Int>()

    while (!queue.isEmpty()) {
        val (currentSquare, stepsToCurrent) = queue.removeFirst()

        dp[currentSquare] = minOf(dp[currentSquare] ?: Int.MAX_VALUE, stepsToCurrent)

        if(!visited.add(currentSquare)) continue

        (1..6)
            .map { currentSquare + it }
            .filter { it <= 100 }
            .map {
                when {
                    ladders.containsKey(it) -> ladders[it]!! to stepsToCurrent + 1
                    snakes.containsKey(it) -> snakes[it]!! to stepsToCurrent + 1
                    else -> it to stepsToCurrent + 1
                }
            }
            .forEach { queue.add(it) }
    }

    return dp[100] ?: -1
}
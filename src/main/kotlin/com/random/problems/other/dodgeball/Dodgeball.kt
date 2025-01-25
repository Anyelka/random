package com.random.problems.other.dodgeball

import com.random.util.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/other/dodgeball/test"

fun main() {
    val lines = getResourceAsText("$FILE_PATH.in")!!.lines()

    val testCases: Int = lines[0].toInt()

    var result: String
    val results = ArrayList<String>()

    var index = 1

    val totalSolveTime = measureTime {
        for(testCase in 1..testCases) {
            val playerCount: Int = lines[index].toInt()
            index++
            val playerPositions = mutableListOf<MutableList<Int>>()
            for(player in 1..playerCount) {
                val playerCoordinates: MutableList<Int> = lines[index].split(" ").map { it.toInt() }.toMutableList()
                playerPositions.add(playerCoordinates)
                index++
            }

            val initialThrowDirection: Direction = Direction.valueOf(lines[index])
            index++
            val initialPlayer: Int = lines[index].toInt()
            index++

            val testCaseSolveTime = measureTime {
                result = dodgeball(playerPositions, initialThrowDirection, initialPlayer)
            }
            println(result)
            println("Dodgeball solving time for $testCase. test case was: $testCaseSolveTime")

            results.add(result)
        }
    }

    println("Total solve time: $totalSolveTime")

    // compare the result with the expected .out file
    val outputLines = getResourceAsText("$FILE_PATH.out")!!.lines()
    var hasError = false
    for((i, line) in results.withIndex()) {
        val expectedResult = outputLines[i]
        if(line != expectedResult) {
            System.err.println("$i. result is wrong: it should be $expectedResult but it is: $line")
            hasError = true
        }
    }
    if(!hasError) {
        println("PERFECT SOLUTION!")
    }

    // write result to file:
    /*File("$FILE_PATH.out").writeText(results.joinToString("\n"))*/
}

fun dodgeball(playerPositions: MutableList<MutableList<Int>>, initialThrowDirection: Direction, initialPlayerIndex: Int): String {
    var throwCount = 0

    // new list to keep track of all the remaining eligible players:
    //      with only iterating over the remaining players, we get a faster solution
    val playersLeft = playerPositions.toMutableList()
    // initial conditions

    var player = playersLeft[initialPlayerIndex - 1]
    var throwDirection = initialThrowDirection

    var canThrowToNextPerson = true
    while (canThrowToNextPerson) {
        playersLeft.remove(player)

        var nextClosestPlayer: MutableList<Int>? = null

        for (direction in 0..7) {
            throwDirection = throwDirection.nextDirection()

            for (nextPlayer in playersLeft) {
                if (canThrowToNextPlayer(player, nextPlayer, throwDirection)
                        && isPlayerCloser(player, nextPlayer, nextClosestPlayer)) {
                    nextClosestPlayer = nextPlayer
                }
            }

            if (nextClosestPlayer != null) {
                break
            }
        }

        if (nextClosestPlayer == null) {
            canThrowToNextPerson = false
        } else {
            throwCount++
            player = nextClosestPlayer
            throwDirection = throwDirection.reverseDirection()
        }

    }

    return "$throwCount ${playerPositions.indexOf(player) + 1}"
}

fun canThrowToNextPlayer(player: MutableList<Int>, nextPlayer: MutableList<Int>, throwDirection: Direction): Boolean {
    return when (throwDirection) {
        Direction.N -> player[0] == nextPlayer[0] && nextPlayer[1] > player[1]
        Direction.NE -> (player[0] - nextPlayer[0]) == (player[1] - nextPlayer[1]) && nextPlayer[0] > player[0]

        Direction.E -> player[1] == nextPlayer[1] && nextPlayer[0] > player[0]
        Direction.SE, -> (player[0] - nextPlayer[0]) == (nextPlayer[1] - player[1]) && player[0] < nextPlayer[0]

        Direction.S -> player[0] == nextPlayer[0] && nextPlayer[1] < player[1]
        Direction.SW -> (player[0] - nextPlayer[0]) == (player[1] - nextPlayer[1]) && nextPlayer[0] < player[0]

        Direction.W -> player[1] == nextPlayer[1] && nextPlayer[0] < player[0]
        Direction.NW -> (player[0] - nextPlayer[0]) == (nextPlayer[1] - player[1]) && player[0] > nextPlayer[0]
    }
}

fun isPlayerCloser(player: MutableList<Int>, nextPlayer: MutableList<Int>, nextClosestPlayer: MutableList<Int>?): Boolean {
    if (nextClosestPlayer == null) {
        return true
    }
    return player.distanceTo(nextPlayer) < player.distanceTo(nextClosestPlayer)
}

fun MutableList<Int>.distanceTo(other: MutableList<Int>): Long {
    return (this[0] - other[0]).absoluteValue().toLong() + (this[1] - other[1]).absoluteValue().toLong()
}

fun Int.absoluteValue(): Int {
    return if(this < 0) -this else this
}

enum class Direction(private val x: Int, private val y: Int, private val index: Int) {
    NE(1,1, 0),
    E(1,0, 1),
    SE(1, -1, 2),
    S(0, -1, 3),
    SW(-1, -1, 4),
    W(-1, 0, 5),
    NW(-1, 1, 6),
    N(0, 1, 7);

    fun reverseDirection(): Direction {
        return entries.find { it.x == -this.x  && it.y == -this.y }!!
    }

    fun nextDirection(): Direction {
        return rotateClockwise()
    }

    private fun rotateClockwise() = if (this.index == 7) NE else getNextDirection(this.index + 1)

    private fun getNextDirection(i: Int): Direction {
        return entries.find { it.index == i }!!
    }

}
package com.random.problems.other.dodgeball

import com.random.util.getResourceAsText
import java.io.File
import kotlin.math.absoluteValue

const val EXAMPLE_FILE_PATH = "/other/dodgeball/example"
const val EXAMPLE_BIG_FILE_PATH = "/other/dodgeball/example_big"
const val TEST_FILE_PATH = "/other/dodgeball/test"

val EXAMPLE_FILE_PATHS = listOf(EXAMPLE_FILE_PATH, EXAMPLE_BIG_FILE_PATH)

enum class Direction(val x: Int, val y: Int, val index: Int) {
    N(0,1, 0),
    NE(1,1, 1),
    E(1,0, 2),
    SE(1,-1, 3),
    S(0,-1, 4),
    SW(-1,-1, 5),
    W(-1,0, 6),
    NW(-1,1, 7);

    fun rotate(): Direction {
        if(this.index == 7) return N
        return Direction.entries.find { it.index == this.index + 1 }!!
    }

    fun inverse(): Direction {
        return Direction.entries.find { it.x == -this.x  && it.y == -this.y }!!
    }
}

class Input(val players: List<Pair<Int,Int>>,
            val startDirection: Direction,
            val startingPlayerIndex: Int) {
}

class Output(val throwCount: Int, val lastPlayerIndex: Int) {}

fun main() {
    EXAMPLE_FILE_PATHS.forEach { test(it) }
    solve(TEST_FILE_PATH)
}

fun getInputForEachTestCase(filePath: String): List<Input> {
    val lines = getResourceAsText("$filePath.in")!!.lines()

    val inputs = mutableListOf<Input>()
    var currentPlayers = mutableListOf<Pair<Int,Int>>()
    var i = 1
    while(i <= lines.lastIndex) {
        val nextLine = lines[i]
        if(nextLine.contains(" ")) {
            val playerCoordinates = nextLine
                .split(" ")
                .map { it.toInt() }
                .zipWithNext()[0]
            currentPlayers.add(playerCoordinates)
        }
        if(Direction.entries.any { it.name ==  nextLine}) {
            val startDirection = Direction.valueOf(nextLine)

            i++
            inputs.add(Input(
                currentPlayers.toMutableList(),
                startDirection,
                lines[i].toInt()
            ))
            currentPlayers = mutableListOf()
        }
        i++
    }
    return inputs
}

fun test(filePath: String) {
    val inputs = getInputForEachTestCase(filePath)
    val outputs = inputs.map { solve(it)}
    compareOutput(outputs, filePath)
}

fun solve(filePath: String) {
    val inputs = getInputForEachTestCase(filePath)
    val outputs = inputs.map { solve(it)}
    writeOutput(outputs, filePath)
}

fun solve(input: Input): Output {
    var currentPlayer = input.players[input.startingPlayerIndex-1]

    var throwDirection = input.startDirection
    var canThrow = true
    var totalThrows = 0

    val playersLeft = input.players.toMutableList()

    while(canThrow) {
        playersLeft.remove(currentPlayer)

        var nextClosestPlayer: Pair<Int,Int>? = null

        for (direction in 0..7) {
            throwDirection = throwDirection.rotate()
            // check if it can be thrown in the current direction
            //      - if yes:
            //          - find closest player
            for (nextPlayer in playersLeft) {
                if (isInDirection(currentPlayer, nextPlayer, throwDirection)
                    && isCloserThen(currentPlayer, nextPlayer, nextClosestPlayer)) {
                    nextClosestPlayer = nextPlayer
                }
            }
            //          - throw
            if (nextClosestPlayer != null) {
                break
            }
            //      - if no: rotate to next direction
        }

        if(nextClosestPlayer == null) {
            // we did not find anyone in any directions to throw to => game ends
            canThrow = false
        } else {
            totalThrows++
            currentPlayer = nextClosestPlayer
            throwDirection = throwDirection.inverse()
        }
    }

    return Output(totalThrows, input.players.indexOf(currentPlayer) + 1)
}

fun isInDirection(currentPlayer: Pair<Int,Int>, nextPlayer: Pair<Int,Int>, direction: Direction): Boolean {
    return when(direction) {
        Direction.N -> currentPlayer.first == nextPlayer.first && currentPlayer.second < nextPlayer.second
        Direction.S -> currentPlayer.first == nextPlayer.first && currentPlayer.second > nextPlayer.second
        Direction.E -> currentPlayer.first < nextPlayer.first && currentPlayer.second == nextPlayer.second
        Direction.W -> currentPlayer.first > nextPlayer.first && currentPlayer.second == nextPlayer.second
        Direction.NE -> currentPlayer.first < nextPlayer.first && currentPlayer.second < nextPlayer.second
                && currentPlayer.isDiagonalTo(nextPlayer)
        Direction.SE -> currentPlayer.first < nextPlayer.first && currentPlayer.second > nextPlayer.second
                && currentPlayer.isDiagonalTo(nextPlayer)
        Direction.SW -> currentPlayer.first > nextPlayer.first && currentPlayer.second > nextPlayer.second
                && currentPlayer.isDiagonalTo(nextPlayer)
        Direction.NW -> currentPlayer.first > nextPlayer.first && currentPlayer.second < nextPlayer.second
                && currentPlayer.isDiagonalTo(nextPlayer)
    }
}

fun isCloserThen(currentPlayer: Pair<Int, Int>, nextPlayer: Pair<Int, Int>, nextClosestPlayer: Pair<Int, Int>?): Boolean {
    if(nextClosestPlayer == null) return true
    return nextPlayer.distanceTo(currentPlayer) < nextClosestPlayer.distanceTo(currentPlayer)
}

fun Pair<Int,Int>.distanceTo(other: Pair<Int,Int>): Long {
    return (this.first-other.first).absoluteValue.toLong() + (this.second-other.second).absoluteValue.toLong()
}

fun Pair<Int,Int>.isDiagonalTo(other: Pair<Int,Int>): Boolean {
    return (this.first-other.first).absoluteValue.toLong() == (this.second-other.second).absoluteValue.toLong()
}

fun compareOutput(outputs: List<Output>, filePath: String) {
    val lines = getResourceAsText("$filePath.out")!!.lines()
    var hasError = false
    for(i in outputs.indices) {
        val output = outputs[i]
        val expectedOutput = lines[i]
            .split(" ")
            .map { it.toInt() }
        if(output.throwCount != expectedOutput[0]) {
            println("Wrong solution for $filePath test case: ${i+1}")
            println("Invalid throwCount: ${output.throwCount}, expected: ${expectedOutput[0]}")
            hasError = true
            break
        }
        if(output.lastPlayerIndex != expectedOutput[1]) {
            println("Wrong solution for $filePath test case: ${i+1}")
            println("Invalid last player index: ${output.lastPlayerIndex}, expected: ${expectedOutput[1]}")
            hasError = true
            break
        }
    }
    if(!hasError) {
        println("Good solution for $filePath")
    }
}

fun writeOutput(outputs: List<Output>, filePath: String) {
    val lines = outputs.map { "${it.throwCount} ${it.lastPlayerIndex}" }
    File("test.out").writeText(lines.joinToString("\n"))
}
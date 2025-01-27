package com.random.problems.adventOfCode.twentyFour.day14

import com.random.problems.adventOfCode.twentyFour.util.Grid
import com.random.problems.adventOfCode.twentyFour.util.Position
import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day14/TestCase1"
const val OUTPUT_FILE_PATH = "FinalPositionsMap"

const val STEP_COUNT = 100

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()
    val game = InputConverter().getGame(lines)

    Part1().run(game)
    // Part2().run(game)
}


class InputConverter() {
    private val boardSizeRegex = """w=(\d+) h=(\d+)""".toRegex()
    private val robotRegex = """p=(\d+),(\d+) v=(-?\d+),(-?\d+)""".toRegex()
    fun getGame(lines: List<String>) = Game(
        lines.drop(1).map {
            robotRegex.find(it)!!.destructured.let { (x, y, vx, vy) -> Robot.of(x, y, vx, vy) }
        }.toList(),
        boardSizeRegex.find(lines[0])!!.destructured.let { (width, height) ->
            Position(width.toInt(), height.toInt())
        })

}

class OutputConverter() {
    fun getGrid(positions: List<Position>, boardSize: Position): Grid {
        val occurrenceMap = positions.groupingBy { it }.eachCount()
        val array = Array(boardSize.y) { Array(boardSize.x) { '.' } }
        for (i in 0..<boardSize.y) {
            for (j in 0..<boardSize.x) {
                occurrenceMap[Position(j, i)]?.let { array[i][j] = it.digitToChar() }
            }
        }
        return Grid(array)
    }
}

class Part1 : Part("Part1") {
    override fun totalSafetyFactor(game: Game): Int {
        val finalPositions = game.robots.map { it.move(STEP_COUNT, game.boardSize) }

        //writeToFile(OUTPUT_FILE_PATH, OutputConverter().getGrid(finalPositions, game.boardSize))

        val occurrenceMap: Map<Position, Int> = finalPositions.groupingBy { it }.eachCount()

        return occurrenceMap.entries
            .groupBy { it.key.groupByQuadrants(game) }
            .filterKeys { it != "Drop" }
            .map { occurrencesInQuadrant -> occurrencesInQuadrant.value.sumOf { it.value } }
            .reduce { acc, num -> acc * num }
    }

    private fun Position.groupByQuadrants(game: Game): String {
        val centerX = (game.boardSize.x) / 2
        val centerY = (game.boardSize.y) / 2
        val (x, y) = this
        return when {
            x < centerX && y < centerY -> "Q1"
            x > centerX && y < centerY -> "Q2"
            x < centerX && y > centerY -> "Q3"
            x > centerX && y > centerY -> "Q4"
            else -> "Drop"
        }
    }

}

class Part2 : Part("Part2") {
    override fun totalSafetyFactor(game: Game): Int {
        TODO("Not yet implemented")
    }

}

abstract class Part(val name: String) {
    open fun run(game: Game) {
        runAndLogTime(Runnable {
            val result = totalSafetyFactor(game)
            println("$name - The total safety factor: $result")
        })
    }

    abstract fun totalSafetyFactor(game: Game): Int
}

data class Game(val robots: List<Robot>, val boardSize: Position)

data class Robot(val start: Position, val velocity: Position) {
    companion object {
        fun of(x: String, y: String, vx: String, vy: String): Robot =
            Robot(Position(x.toInt(), y.toInt()), Position(vx.toInt(), vy.toInt()))
    }

    fun move(steps: Int, boardSize: Position): Position =
        (start + velocity * steps).let { it.onBoard(it.x, it.y, boardSize) }

    private fun Position.onBoard(x: Int, y: Int, boardSize: Position): Position =
        Position(getFinalPosition(x, boardSize.x), getFinalPosition(y, boardSize.y))

    private fun getFinalPosition(coordinate: Int, maxCoordinate: Int): Int =
        (coordinate % maxCoordinate)
            .let { if (it < 0) it + maxCoordinate else it }
            .let { if (it == maxCoordinate) 0 else it }
}

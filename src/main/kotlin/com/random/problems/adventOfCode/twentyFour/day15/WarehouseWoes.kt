package com.random.problems.adventOfCode.twentyFour.day15

import com.random.problems.adventOfCode.twentyFour.util.*
import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day15/TestCase1"
const val OUTPUT_FILE_PATH = "FinalMapPositions"

const val MOVE_UP = '^'
const val MOVE_RIGHT = '>'
const val MOVE_DOWN = 'v'
const val MOVE_LEFT = '<'

const val ROBOT = '@'
const val BOX = 'O'
const val WALL = '#'
const val EMPTY = '.'

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()
    val game = InputConverter.parseInput(lines)

    Part1.run(game)
    // Part2().run(game)

}

object InputConverter {
    fun parseInput(lines: List<String>) =
        splitInput(lines).let { (map, moves) -> Game(Grid.of(map), convertMoves(moves)) }

    private fun splitInput(lines: List<String>): Pair<List<String>, List<String>> {
        val index = lines.indexOf("")
        return if (index == -1) {
            lines to emptyList()
        } else {
            lines.take(index) to lines.drop(index + 1)
        }
    }

    private fun convertMoves(moves: List<String>): List<Direction> =
        moves.joinToString("").toCharArray().map { Direction.of(it) }

    private fun Direction.Companion.of(char: Char): Direction {
        if (char == MOVE_UP) return Direction.UP
        if (char == MOVE_RIGHT) return Direction.RIGHT
        if (char == MOVE_DOWN) return Direction.DOWN
        if (char == MOVE_LEFT) return Direction.LEFT
        throw IllegalArgumentException("Unknown direction: $char")
    }
}

// Part 1:
//      - determine the state of the map after the robot makes all of its moves:
//          - make 1 move:
//              - check whether the robot can make the next move: check the next non-O position in the direction of move
//                  - if its a # nothing happens
//                  - if its a . the robot can make the move
//                      - put the robot in the next position
//                      - free up the current position of the robot
//                      - check whether the next position in the direction is a O
//                          - if its an O, put O into the next non-O position in the direction of move
//                          - if its non-O, do nothing
object Part1 {
    fun run(game: Game) {
        runAndLogTime(
            Runnable {
                game.makeMoves()
                writeToFile(OUTPUT_FILE_PATH, game.grid)
                val result = game.findAllBoxes().sumOf { it.gps() }
                println("Total gps: $result")
            })
    }

}

private fun Position.gps(): Long = 100 * this.y.toLong() + this.x.toLong()

data class Game(val grid: Grid, val moves: List<Direction>) {
    fun findAllBoxes() = grid.findAll(BOX)

    fun makeMoves() {
        moves.forEach { makeMove(it) }
    }

    private fun makeMove(direction: Direction) {
        val robot = getRobotPosition()
        val nextPosition = robot.move(direction)
        val nextInLineSkippingBoxes = getNextInLineSkippingBoxes(nextPosition, direction)

        if (grid[nextInLineSkippingBoxes] == EMPTY) {
            grid.moveRobot(robot, nextPosition)
            if (nextInLineSkippingBoxes != nextPosition) grid[nextInLineSkippingBoxes] = BOX
        }
    }

    private fun getRobotPosition(): Position = grid.positionOf(ROBOT)

    private fun getNextInLineSkippingBoxes(position: Position, direction: Direction): Position =
        generateSequence(position) { it.move(direction) }
            .dropWhile { grid[it] == BOX }
            .first()

    private fun Grid.moveRobot(position: Position, nextPosition: Position) {
        grid[nextPosition] = ROBOT
        grid[position] = EMPTY
    }
}
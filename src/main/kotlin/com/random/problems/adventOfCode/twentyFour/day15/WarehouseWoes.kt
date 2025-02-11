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

const val BOX_LEFT = '['
const val BOX_RIGHT = ']'

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()
    val game = InputConverter.parseInput(lines)

    //Part1.run(game)
    Part2.run(game)

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
object Part1: Part("Part1") {
    override fun Game.findAllBoxes() = grid.findAll(BOX)

    override fun Game.makeMove(direction: Direction) {
        val robot = getRobotPosition()
        val nextPosition = robot.move(direction)
        val nextInLineSkippingBoxes = getNextInLineSkippingBoxes(nextPosition, direction)

        if (grid[nextInLineSkippingBoxes] == EMPTY) {
            moveRobot(robot, nextPosition)
            moveBoxes(nextPosition, direction, nextInLineSkippingBoxes)
        }
    }

    private fun Game.getNextInLineSkippingBoxes(position: Position, direction: Direction): Position =
        generateSequence(position) { it.move(direction) }
            .dropWhile { grid[it] == BOX }
            .first()

    private fun Game.moveBoxes(nextPosition: Position, direction: Direction, nextInLineSkippingBoxes: Position) {
        if (nextInLineSkippingBoxes != nextPosition) grid[nextInLineSkippingBoxes] = BOX
    }
}

// Part2:
//      - determine the state of the map after the robot makes all of its moves:
//          - make 1 move:
//              - check whether the robot can make the next move:
//              -> check the next position in the direction: if its a box '[' or ']', check the neighbors of each recursively
//                  - if we find a # in any neighbor, nothing happens (cant make move)
//                  - if we finish the search and no # is found -> robot can make the move
//                      - move all objects to the next position:
//                          - start from the empty space right before the robot (first in opposite direction)
//                              - check whether the next position in the direction is a box '[' or ']'
//

object Part2: Part("Part2") {
    private fun extend(game: Game): Game = Game(widenGrid(game.grid), game.moves)

    private fun widenGrid(grid: Grid): Grid {
        val fields: Array<Array<Char>> = grid.fields
        val newFields = Array(fields.size) { Array(fields[0].size * 2) { '.' } }
        for ((i, row) in fields.withIndex()) {
            for((j, col) in fields[i].withIndex()) {
                val nextColIndex = j * 2
                when (fields[i][j]) {
                    ROBOT -> newFields[i][nextColIndex] = ROBOT
                    BOX -> {
                        newFields[i][nextColIndex] = BOX_LEFT
                        newFields[i][nextColIndex + 1] = BOX_RIGHT
                    }
                    WALL -> {
                        newFields[i][nextColIndex] = WALL
                        newFields[i][nextColIndex + 1] = WALL
                    }
                }
            }
        }
        return Grid(newFields)
    }

    override fun run(game: Game) = super.run(extend(game))
    override fun Game.findAllBoxes(): List<Position> = grid.findAll(BOX_LEFT)

    override fun Game.makeMove(direction: Direction) {
        val robot = getRobotPosition()
        val canMakeMove = canMakeMove(robot, direction)

        if(canMakeMove) {
            moveBoxes(robot, direction)
        }
    }

    private fun Game.canMakeMove(position: Position, direction: Direction): Boolean {
        val queue = ArrayDeque<Position>()
        queue.add(position.move(direction))

        while(!queue.isEmpty()) {
            val nextPosition = queue.removeFirst();
            val nextChar = grid.charAt(nextPosition)
            if(nextChar == WALL) {
                return false
            }
            if(nextChar == EMPTY) {
                continue
            }
            val next = nextPosition.move(direction)
            if(direction.isVertical()) {
                if(nextChar == BOX_LEFT) {
                    queue.add(next.move(Direction.RIGHT))
                } else if(nextChar == BOX_RIGHT) {
                    queue.add(next.move(Direction.LEFT))
                }
            }
            queue.add(next)
        }
        return true

    }

    private fun Game.moveBoxes(position: Position, direction: Direction) {
        val queue = ArrayDeque<Pair<Position, Char>>()
        val visited = mutableMapOf<Position, Char>()
        queue.add(position.move(direction.reverse()) to EMPTY)

        while(!queue.isEmpty()) {
            val (currentPos, currentChar) = queue.removeFirst();

            if(currentChar == WALL || (visited.containsKey(currentPos) && visited[currentPos] != EMPTY)) {
                continue
            }

            val (nextPos, nextChar) = currentPos.move(direction).let { it to grid.charAt(it)!! }

            if(nextChar != EMPTY) {
                queue.add(nextPos to nextChar)
            }

            if(direction.isVertical()) {
                if(nextChar == BOX_RIGHT) {
                    val onTheLeft = currentPos.move(Direction.LEFT)
                    if(!visited.containsKey(onTheLeft)) queue.add(onTheLeft to EMPTY)
                }
                if(nextChar == BOX_LEFT) {
                    val onTheRight = currentPos.move(Direction.RIGHT)
                    if(!visited.containsKey(onTheRight)) queue.add(currentPos.move(Direction.RIGHT) to EMPTY)
                }
            }

            grid[nextPos] = currentChar

            visited[currentPos] = currentChar
        }

    }
}

abstract class Part(name: String) {
    open fun run(game: Game) = runAndLogTime(Runnable { runGame(game) })

    private fun runGame(game: Game) {
        game.makeMoves()
        //writeToFile(OUTPUT_FILE_PATH, game.grid)
        val result = game.findAllBoxes().sumOf { it.gps() }
        println("Total gps: $result")
    }

    abstract fun Game.findAllBoxes(): List<Position>
    abstract fun Game.makeMove(direction: Direction)


    private fun Position.gps(): Long = 100 * this.y.toLong() + this.x.toLong()
    private fun Game.makeMoves() = moves.forEach { makeMove(it) }

    fun Game.getRobotPosition(): Position = grid.positionOf(ROBOT)

    fun Game.moveRobot(position: Position, nextPosition: Position) {
        grid[nextPosition] = ROBOT
        grid[position] = EMPTY
    }

}

data class Game(val grid: Grid, val moves: List<Direction>)
package com.random.problems.adventOfCode.twentyFour.day16

import com.random.problems.adventOfCode.twentyFour.util.Direction
import com.random.problems.adventOfCode.twentyFour.util.Grid
import com.random.problems.adventOfCode.twentyFour.util.Position
import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day16/TestCase1"

const val START_CHAR = 'S'
const val END_CHAR = 'E'
const val WALL = '#'

val START_DIRECTION = Direction.RIGHT

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val maze = Grid.of(lines)

    Part1.run(maze)
}

object Part1 {
    fun run(maze: Grid) = runAndLogTime(Runnable { runGame(maze) })

    fun runGame(maze: Grid) {
        val result = getLowestScore(maze)
        println("Part1: lowest score: $result")
    }

    private fun getLowestScore(maze: Grid): Int {
        val start = maze.positionOf(START_CHAR)
        val end = maze.positionOf(END_CHAR)
        return getLowestScoreToReach(maze, start, end, START_DIRECTION)
    }

    private fun getLowestScoreToReach(
        maze: Grid,
        start: Position,
        end: Position,
        startDirection: Direction
    ): Int {
        val dp = mutableMapOf<Position, Int>().apply { this[start] = 0 }

        val queue = ArrayDeque<Pair<Position, Direction>>().apply { this.add(start to startDirection) }

        while (!queue.isEmpty()) {
            val (position, direction) = queue.removeFirst()

            mutableMapOf(direction to 1, direction.next() to 1001, direction.previous() to 1001)
                .forEach { (nextDirection, stepValue) ->
                    position.move(nextDirection)
                        .takeIf { maze.charAt(it) != WALL }
                        ?.let { nextPosition ->
                            (dp[nextPosition] to dp[position]!! + stepValue)
                                .also { (prevMin, nextMin) -> dp[nextPosition] = listOfNotNull(prevMin, nextMin).min() }
                                .takeIf { (prevMin, nextMin) -> (prevMin == null || prevMin > nextMin) }
                                ?.apply { queue.add(nextPosition to nextDirection) }
                        }
                }
        }

        return dp[end]!!
    }

}
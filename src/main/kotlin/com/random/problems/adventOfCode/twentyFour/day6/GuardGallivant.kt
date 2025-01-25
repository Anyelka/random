package com.random.problems.adventOfCode.twentyFour.day6

import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day6/TestCase1"

private const val OBSTACLE = '#'
private const val START = '^'

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val map = Map.of(lines)
    Part1(map).run()
    Part2(map).run()
}

abstract class Part(val map: Map) {
    abstract fun run()
}
class Part1(map: Map): Part(map) {
    override fun run() {
        println("The guard visits ${countStepsTaken(map)} distinct positions on the map.")
    }

    private fun countStepsTaken(map: Map): Int {
        return getVisitedSteps(map).distinctBy { it.fromPosition }.count()
    }
}

class Part2(map: Map): Part(map) {
    override fun run() {
        println("There are ${countPossibleLoops(map)} possible loops.")
    }

    private fun countPossibleLoops(map: Map): Int {
        val visitedSteps = getVisitedSteps(map).distinctBy { it.fromPosition }
        return visitedSteps.drop(1).count { isPossibleLoop(map.addObstacleAtPosition(it.fromPosition)) }
    }

    private fun Map.addObstacleAtPosition(fromPosition: Position): Map {
        val newFields = fields.map { it.copyOf() }.toTypedArray()
        newFields[fromPosition.y][fromPosition.x] = OBSTACLE
        return Map(newFields)
    }
}

private fun getFirstStep(map: Map): Step = Step(getStartPosition(map), Direction.UP)

private fun getStartPosition(map: Map): Position = map.fields.mapIndexedNotNull { rowIndex, line ->
        line.indexOf(START).takeIf { it != -1 }?.let { colIndex -> Position(colIndex, rowIndex) }
    }.first()


private fun getVisitedSteps(map: Map): Set<Step> {
    var step = getFirstStep(map)
    val visitedSteps = mutableSetOf<Step>()
    while (step.isIn(map)) {
        visitedSteps.add(step)
        step = step.take(map)
    }
    return visitedSteps
}

private fun isPossibleLoop(map: Map): Boolean {
    var step = getFirstStep(map)
    val visitedSteps = mutableSetOf<Step>()
    while (step.isIn(map)) {
        if(!visitedSteps.add(step)) {
            return true
        }
        step = step.take(map)
    }
    return false
}


data class Step(val fromPosition: Position, val direction: Direction) {
    private companion object {
        val OUT_OF_MAP = Step(Position.OUT_OF_MAP, Direction.NULL)
    }

    fun take(map: Map): Step {
        val nextPosition = getNextPosition()
        return (if (!nextPosition.isIn(map)) {
            OUT_OF_MAP
        } else if (nextPosition.isObstacle(map)) {
            rotate()
        } else {
            next()
        })
    }
    fun next(): Step = Step(getNextPosition(), direction)
    private fun rotate(): Step = Step(fromPosition, direction.rotate())
    private fun getNextPosition(): Position = fromPosition.move(direction)
    fun isIn(map: Map): Boolean = fromPosition.isIn(map)
}

data class Map(val fields: Array<Array<Char>>) {
    companion object {
        fun of(lines: List<String>) = Map(lines.map { it.toCharArray().toTypedArray() }.toTypedArray())
    }

    fun checkIfInBounds(position: Position)
        = position.y >= 0 && position.y < fields.size && position.x >= 0 && position.x < fields[0].size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Map

        return fields.contentDeepEquals(other.fields)
    }

    override fun hashCode(): Int {
        return fields.contentDeepHashCode()
    }

}

data class Position(val x: Int, val y: Int) {
    companion object {
        val OUT_OF_MAP = Position(-1, -1)
    }

    fun move(direction: Direction): Position = Position(x + direction.x, y + direction.y)

    fun isIn(map: Map) = map.checkIfInBounds(this)

    fun isObstacle(map: Map) = map.fields[y][x] == OBSTACLE
}

enum class Direction(val x: Int, val y: Int) {
    UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0), NULL(0, 0);

    fun rotate(): Direction {
        return when(this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
            else -> NULL
        }
    }
}


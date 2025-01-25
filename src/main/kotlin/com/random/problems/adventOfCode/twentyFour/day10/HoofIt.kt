package com.random.problems.adventOfCode.twentyFour.day10

import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day10/TestCase1"

const val LOWEST = 0
const val HIGHEST = 9
fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()
    val map = TopographicMap.of(lines)
    val totalTrailheadScore = Part1().getTotalTrailheadScore(map)
    println("Total trailhead score: $totalTrailheadScore")

    val totalRatingScore = Part2().getTotalTrailheadScore(map)
    println("Total trailhead score with ratings: $totalRatingScore")
}

class Part1: Part() {
    override fun Position.score(map: TopographicMap) = getReachableTops(map).distinctBy { it }.count()
}

class Part2: Part() {
    override fun Position.score(map: TopographicMap) = getReachableTops(map).count()
}

abstract class Part {
    fun getTotalTrailheadScore(map: TopographicMap) = map.getTrailHeads().sumOf { it.score(map) }

    abstract fun Position.score(map: TopographicMap): Int

    protected fun Position.getReachableTops(map: TopographicMap): List<Position> {
        if (map.heightAt(this) == HIGHEST) {
            return listOf(this)
        }
        return Direction.entries.toTypedArray().map { this.getReachableTopsTowards(it, map) }.flatten()
    }

    private fun Position.getReachableTopsTowards(direction: Direction, map: TopographicMap): List<Position> {
        val nextPosition = this.next(direction)
        return if(nextPosition.isIn(map) && nextPosition.isNextHeightOf(this, map))
            nextPosition.getReachableTops(map)
        else emptyList()
    }
}


data class TopographicMap(val fields: Array<Array<Int>>) {
    companion object {
        fun of(lines: List<String>)
            = TopographicMap(lines.map { it.toCharArray().map { c -> c.digitToInt() }.toTypedArray() }.toTypedArray())
    }

    fun heightAt(position: Position) = fields[position.y][position.x]

    fun getTrailHeads(): List<Position> = this.fields.withIndex().map { (i,line) ->
                line.withIndex().filter { (j, c) -> c == LOWEST }.map { (j, c) -> Position(j, i)  }
            }.flatten()


    fun checkIfInBounds(position: Position)
            = position.y >= 0 && position.y < fields.size && position.x >= 0 && position.x < fields[0].size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TopographicMap

        return fields.contentDeepEquals(other.fields)
    }

    override fun hashCode(): Int {
        return fields.contentDeepHashCode()
    }
}
data class Position(val x: Int, val y: Int) {
    companion object {
        val NULL = Position(-1, -1)
    }
    fun next(direction: Direction) = Position(x + direction.x, y + direction.y)

    fun isIn(map: TopographicMap) = map.checkIfInBounds(this)

    fun isNextHeightOf(other: Position, map: TopographicMap) = map.heightAt(this) == map.heightAt(other) + 1
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

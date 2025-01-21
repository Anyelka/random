package com.random.problems.adventOfCode.twentyFour.util


data class Position(val x: Int, val y: Int) {
    operator fun plus(direction: Direction) = this.move(direction)

    companion object {
        val OUT_OF_MAP = Position(-1, -1)
    }

    fun move(direction: Direction): Position = Position(x + direction.x, y + direction.y)

    fun isIn(regions: MutableMap<Pair<Int, Char>, List<Position>>) = this in regions.values.flatten()

}
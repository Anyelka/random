package com.random.problems.adventOfCode.twentyFour.util


data class Position(val x: Int, val y: Int) {
    operator fun plus(position: Position) = this.move(position)
    operator fun plus(direction: Direction) = this.move(direction)
    operator fun times(multiplier: Int): Position = Position(this.x * multiplier, this.y * multiplier)

    companion object {
        val NULL = Position(0, 0)
        val OUT_OF_MAP = Position(-1, -1)
    }

    private fun move(move: Position): Position = Position(this.x + move.x, this.y + move.y)
    fun move(direction: Direction): Position = Position(x + direction.x, y + direction.y)

    fun isIn(regions: MutableMap<Pair<Int, Char>, List<Position>>) = this in regions.values.flatten()

    fun isPast(other: Position) = this.x > other.x || this.y > other.y

}
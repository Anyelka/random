package com.random.problems.adventOfCode.twentyFour.util

data class PositionLong(val x: Long, val y: Long) {
    operator fun minus(position: PositionLong) = this.moveBack(position)
    operator fun plus(position: PositionLong) = this.move(position)
    operator fun plus(direction: Direction) = this.move(direction)

    companion object {
        val NULL = PositionLong(0, 0)
        fun of(value: Long) = PositionLong(value, value)
    }

    private fun move(move: PositionLong): PositionLong = PositionLong(this.x + move.x, this.y + move.y)
    private fun moveBack(move: PositionLong): PositionLong = PositionLong(this.x - move.x, this.y - move.y)
    fun move(direction: Direction): PositionLong = PositionLong(x + direction.x, y + direction.y)

    fun isPast(other: PositionLong) = this.x > other.x || this.y > other.y

}
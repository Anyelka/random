package com.random.problems.adventOfCode.twentyFour.util

enum class Direction(val x: Int, val y: Int) {
    UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0), NULL(0, 0);

    fun reverse() = this.next().next()
    fun previous() = when(this) {
        UP -> LEFT
        RIGHT -> UP
        DOWN -> RIGHT
        LEFT -> DOWN
        else -> NULL
    }

    fun next() = when(this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
        else -> NULL
    }

    companion object
}
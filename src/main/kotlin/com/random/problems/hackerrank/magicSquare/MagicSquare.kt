package com.random.problems.hackerrank.magicSquare

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(
            arrayOf(5, 3, 4),
            arrayOf(1, 5, 8),
            arrayOf(6, 4, 2)
        ) to 7,
        arrayOf(
            arrayOf(4, 9, 2),
            arrayOf(3, 5, 7),
            arrayOf(8, 1, 5)
        ) to 1,
        arrayOf(
            arrayOf(4, 8, 2),
            arrayOf(4, 5, 7),
            arrayOf(6, 1, 6)
        ) to 4
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().formingMagicSquare(s) }
    }

}
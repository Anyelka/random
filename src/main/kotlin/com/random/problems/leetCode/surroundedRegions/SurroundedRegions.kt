package com.random.problems.leetCode.surroundedRegions

import com.random.util.testInPlace


fun main() {
    val input = listOf(
        // 1.
        arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'O', 'O', 'X'),
            charArrayOf('X', 'X', 'O', 'X'),
            charArrayOf('X', 'O', 'X', 'X')
        ) to arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'O', 'X', 'X')
        ),
        // 2.
        arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'O', 'O', 'X'),
            charArrayOf('X', 'O', 'O', 'X'),
            charArrayOf('X', 'X', 'X', 'X')
        ) to arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X')
        ),
        // 3.
        arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'O', 'O', 'O'),
            charArrayOf('X', 'O', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X')
        ) to arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'O', 'O', 'O'),
            charArrayOf('X', 'O', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X')
        ),
        arrayOf(charArrayOf('X')) to arrayOf(charArrayOf('X')),
        arrayOf(charArrayOf('O')) to arrayOf(charArrayOf('O'))
    )

    input.forEach {
        val board = it.first
        it.testInPlace { Solution1().solve(board) }
    }
}
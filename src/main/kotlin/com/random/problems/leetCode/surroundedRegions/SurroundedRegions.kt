package com.random.problems.leetCode.surroundedRegions

import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
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
        arrayOf(charArrayOf('O')) to arrayOf(charArrayOf('O')),
        // 6.
        arrayOf(
            charArrayOf('O','X','O','O','X','O','X','O','O','O'),
            charArrayOf('O','O','O','O','O','X','O','O','X','O'),
            charArrayOf('O','O','O','O','O','O','O','O','X','O'),
            charArrayOf('O','O','O','O','O','O','X','X','X','O'),
            charArrayOf('O','O','O','O','O','X','O','O','O','X'),
            charArrayOf('O','O','O','O','O','O','O','O','O','X'),
            charArrayOf('O','O','O','O','X','O','O','O','O','O'),
            charArrayOf('O','O','O','O','X','O','O','O','X','O'),
            charArrayOf('X','X','O','O','O','O','O','O','O','O'),
            charArrayOf('O','O','O','O','X','O','X','O','O','X')
        ) to arrayOf(
            charArrayOf('O','X','O','O','X','O','X','O','O','O'),
            charArrayOf('O','O','O','O','O','X','O','O','X','O'),
            charArrayOf('O','O','O','O','O','O','O','O','X','O'),
            charArrayOf('O','O','O','O','O','O','X','X','X','O'),
            charArrayOf('O','O','O','O','O','X','O','O','O','X'),
            charArrayOf('O','O','O','O','O','O','O','O','O','X'),
            charArrayOf('O','O','O','O','X','O','O','O','O','O'),
            charArrayOf('O','O','O','O','X','O','O','O','X','O'),
            charArrayOf('X','X','O','O','O','O','O','O','O','O'),
            charArrayOf('O','O','O','O','X','O','X','O','O','X')
        ),
        // 7.
        getBigInput(),
        // 8.
        arrayOf(
            charArrayOf('O','X','O','O','O','X'),
            charArrayOf('O','O','X','X','X','O'),
            charArrayOf('X','X','X','X','X','O'),
            charArrayOf('O','O','O','O','X','X'),
            charArrayOf('X','X','O','O','X','O'),
            charArrayOf('O','O','X','X','X','X')
        ) to arrayOf(
            charArrayOf('O','X','O','O','O','X'),
            charArrayOf('O','O','X','X','X','O'),
            charArrayOf('X','X','X','X','X','O'),
            charArrayOf('O','O','O','O','X','X'),
            charArrayOf('X','X','O','O','X','O'),
            charArrayOf('O','O','X','X','X','X')
        )
    )

    input.forEach {
        val board = it.first
        runAndLogTime{ it.testInPlace { Solution1().solve(board) } }
    }
}
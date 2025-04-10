package com.random.problems.leetCode.wordsearch

import com.random.util.test

fun main() {
    val input = listOf(
        (arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'C', 'S'), charArrayOf('A', 'D', 'E', 'E')) to "ABCCED") to true,
        (arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'C', 'S'), charArrayOf('A', 'D', 'E', 'E')) to "SEE") to true,
        (arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'C', 'S'), charArrayOf('A', 'D', 'E', 'E')) to "ABCB") to false,
        (arrayOf(charArrayOf('a', 'b'), charArrayOf('c', 'd')) to "bacd") to true,
        (arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'E', 'S'), charArrayOf('A', 'D', 'E', 'E')) to "ABCESEEEFS") to true
    )

    input.forEach {
        val (board, word) = it.first
        it.test { Solution2().exist(board, word) }
    }
}
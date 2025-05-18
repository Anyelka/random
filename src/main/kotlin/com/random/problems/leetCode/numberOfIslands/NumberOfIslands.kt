package com.random.problems.leetCode.numberOfIslands

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(
            charArrayOf('1','1','1','1','0'),
            charArrayOf('1','1','0','1','0'),
            charArrayOf('1','1','0','0','0'),
            charArrayOf('0','0','0','0','0')
        ) to 1,
        arrayOf (
            charArrayOf('1','1','0','0','0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'),
            charArrayOf('0', '0', '0', '1', '1')
        ) to 3,
        longInput()
    )

    input.forEach {
        val grid = it.first
        it.test { Solution1().numIslands(grid) }
    }

}
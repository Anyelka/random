package com.random.problems.leetCode.lastStoneWeight

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(2,7,4,1,8,1) to 1,
        intArrayOf(1) to 1,
        intArrayOf(2,2) to 0
    )

    input.forEach {
        val stones = it.first
        it.test { Solution1().lastStoneWeight(stones) }
    }

}
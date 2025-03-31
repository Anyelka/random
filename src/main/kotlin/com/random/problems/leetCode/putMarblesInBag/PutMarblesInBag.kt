package com.random.problems.leetCode.putMarblesInBag

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(1,3,5,1) to 2) to 4L,
        (intArrayOf(1, 3) to 2) to 0L,
        (intArrayOf(54,6,34,66,63,52,39,62,46,75,28,65,18,37,18,13,33,69,19,40,13,10,43,61,72) to 4) to 0L
    )

    input.forEach {
        val (weights, k) = it.first
        it.test { Solution1().putMarbles(weights, k) }
    }

}
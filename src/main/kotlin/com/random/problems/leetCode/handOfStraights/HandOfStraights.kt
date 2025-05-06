package com.random.problems.leetCode.handOfStraights

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(1,2,3,6,2,3,4,7,8) to 3) to true,
        (intArrayOf(1,2,3,4,5) to 4) to false
    )

    input.forEach {
        val (hand, groupSize) = it.first
        it.test { Solution1().isNStraightHand(hand, groupSize) }
    }
}
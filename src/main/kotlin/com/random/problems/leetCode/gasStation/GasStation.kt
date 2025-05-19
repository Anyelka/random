package com.random.problems.leetCode.gasStation

import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(1,2,3,4,5) to intArrayOf(3,4,5,1,2)) to 3,
        (intArrayOf(2,3,4) to intArrayOf(3,4,3)) to -1,
        getBigInput(),
        (intArrayOf(5,8,2,8) to intArrayOf(6,5,6,6)) to 3,
        (intArrayOf(4) to intArrayOf(5)) to -1,
        (intArrayOf(7, 1, 0, 11, 4) to intArrayOf(5, 9, 1, 2, 5)) to 3
    )

    input.forEach {
        val (gas, cost) = it.first
        it.test { Solution1().canCompleteCircuit(gas, cost) }
    }
}
package com.random.problems.leetCode.linkedListCycle

import com.random.util.convertIntArrayToListNodeWithCycle
import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(3,2,0,-4) to 1) to true,
        (intArrayOf(1,2) to 0) to true,
        (intArrayOf(1) to -1) to false
    )

    input.forEach {
        val (array, connecting) = it.first
        val head = convertIntArrayToListNodeWithCycle(array, connecting)
        it.test { Solution().hasCycle(head)  }
    }
}
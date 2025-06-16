package com.random.problems.leetCode.reverseNodesInAKGroup

import com.random.util.convertIntArrayToListNode
import com.random.util.convertListNodeToIntArray
import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(1,2,3,4,5) to 2) to intArrayOf(2,1,4,3,5),
        (intArrayOf(1,2,3,4,5) to 3) to intArrayOf(3,2,1,4,5),
        (intArrayOf(1,2,3,4,5,6) to 3) to intArrayOf(3,2,1,6,5,4),
        (intArrayOf(5) to 1) to intArrayOf(5),
        (intArrayOf(3,9,6,1,1,4,7) to 4) to intArrayOf(1,6,9,3,1,4,7)

    )

    input.forEach {
        val (head, k) = it.first
        it.test { convertListNodeToIntArray(Solution1().reverseKGroup(convertIntArrayToListNode(head), k)) }
    }
}
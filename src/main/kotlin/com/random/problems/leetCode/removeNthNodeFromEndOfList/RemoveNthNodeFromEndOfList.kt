package com.random.problems.leetCode.removeNthNodeFromEndOfList

import com.random.util.convertIntArrayToListNode
import com.random.util.convertListNodeToIntArray
import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(1,2,3,4,5) to 2 to intArrayOf(1,2,3,5),
        intArrayOf(1) to 1 to intArrayOf(),
        intArrayOf(1,2) to 1 to intArrayOf(1),

    )

    input.forEach {
        val input = it.first
        it.test {
           convertListNodeToIntArray(Solution1().removeNthFromEnd(convertIntArrayToListNode(input.first), input.second))
        }
    }
}
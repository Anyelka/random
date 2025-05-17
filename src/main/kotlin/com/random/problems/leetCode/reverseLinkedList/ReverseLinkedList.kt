package com.random.problems.leetCode.reverseLinkedList

import com.random.util.convertIntArrayToListNode
import com.random.util.testLinkedListFromIntArray

fun main() {
    val input = listOf(
        intArrayOf(1,2,3,4,5) to intArrayOf(5,4,3,2,1),
        intArrayOf(1,2) to intArrayOf(2,1),
        intArrayOf() to intArrayOf()
    )

    input.forEach {
        val input = it.first
        it.testLinkedListFromIntArray { Solution1().reverseList(convertIntArrayToListNode(input)) }
    }
}
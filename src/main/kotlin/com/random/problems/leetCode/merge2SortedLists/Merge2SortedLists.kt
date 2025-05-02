package com.random.problems.leetCode.merge2SortedLists

import com.random.util.convertBack
import com.random.util.convertIntArrayToListNode
import com.random.util.convertListNodeToIntArray
import com.random.util.test

fun main() {
    val input = listOf(
        (intArrayOf(1,2,4) to intArrayOf(1,3,4)) to intArrayOf(1,1,2,3,4,4),
        (intArrayOf() to intArrayOf()) to  intArrayOf(),
        (intArrayOf() to intArrayOf(0)) to intArrayOf(0),
        (intArrayOf(1,4,5) to intArrayOf(1,3,4)) to intArrayOf(1,1,3,4,4,5),
        (intArrayOf(1,2,3,4,5) to intArrayOf(1,3,3,4)) to intArrayOf(1,1,2,3,3,3,4,4,5)
    )

    input.forEach {
        val list1 = convertIntArrayToListNode(it.first.first)
        val list2 = convertIntArrayToListNode(it.first.second)
        it.test { convertListNodeToIntArray(Solution1().mergeTwoLists(list1, list2))  }
    }
}
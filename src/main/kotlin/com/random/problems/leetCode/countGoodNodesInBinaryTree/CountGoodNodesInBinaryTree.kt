package com.random.problems.leetCode.countGoodNodesInBinaryTree

import com.random.util.convertArrayToTreeNode
import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(3,1,4,3,null,1,5) to 4,
        arrayOf(3,3,null,4,2) to 3,
        arrayOf<Int?>(1) to 1,
        arrayOf(1,2,3,4,5,null,null,6,null,null,7,9,null,null,10) to 9,
        arrayOf(3,null,4,1,5) to 3,
        arrayOf(3, null, 4, null, 1, 5) to 3
    )

    input.forEach {
        val array = it.first
        it.test { Solution1().goodNodes(convertArrayToTreeNode(array)) }
    }
}
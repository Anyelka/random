package com.random.problems.leetCode.binaryTreeRightSideView

import com.random.util.convertArrayToTreeNode
import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(1,2,3,null,5,null,4) to listOf(1,3,4),
        arrayOf(1,2,3,4,null,null,null,5) to listOf(1,3,4,5),
        arrayOf(1,null,3) to listOf(1,3),
        arrayOf<Int?>() to listOf()
    )

    input.forEach {
        val root = convertArrayToTreeNode(it.first)
        it.test { Solution1().rightSideView(root) }
    }
}
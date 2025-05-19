package com.random.problems.leetCode.diameterOfBinaryTree

import com.random.util.convertArrayToTreeNode
import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf<Int?>(1,2,3,4,5) to 3,
        arrayOf<Int?>(1,2) to 1,
        arrayOf<Int?>(1,2,3,4,5,null,null,6,null,null,7,9,null,null,10) to 6
    )

    input.forEach {
        val root = convertArrayToTreeNode(it.first)
        it.test { Solution1().diameterOfBinaryTree(root) }
    }
}
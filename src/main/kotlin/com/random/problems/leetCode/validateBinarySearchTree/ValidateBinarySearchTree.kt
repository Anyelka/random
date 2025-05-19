package com.random.problems.leetCode.validateBinarySearchTree

import com.random.util.convertArrayToTreeNode
import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf<Int?>(2,1,3) to true,
        arrayOf<Int?>(5,1,4,null,null,3,6) to false,
        arrayOf(5,4,6,null,null,3,7) to false,
        arrayOf<Int?>(2147483647) to true,
        arrayOf(32,26,47,19,null,null,56,null,27) to false
    )

    input.forEach {
        val nums = it.first
        it.test { Solution1().isValidBST(convertArrayToTreeNode(nums)) }
    }
}
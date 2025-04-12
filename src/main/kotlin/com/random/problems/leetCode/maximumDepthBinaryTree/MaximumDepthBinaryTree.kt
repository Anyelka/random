package com.random.problems.leetCode.maximumDepthBinaryTree

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(3, 9, 20, null, null, 15, 7) to 3,
        arrayOf(1, null, 2) to 2,
        emptyArray<Int?>() to 0
    )

    input.forEach {
        val array = it.first
        it.test { Solution1().convertAndGetMaxDepth(array) }
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
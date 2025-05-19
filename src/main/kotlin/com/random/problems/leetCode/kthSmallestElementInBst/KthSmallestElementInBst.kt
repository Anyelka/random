package com.random.problems.leetCode.kthSmallestElementInBst

import com.random.util.convertArrayToTreeNode
import com.random.util.test

fun main() {
    val input = listOf(
        (arrayOf(3,1,4,null,2) to 1) to 1,
        (arrayOf(5,3,6,2,4,null,null,1) to 3) to 3,
    )

    input.forEach {
        val (root, k) = it.first
        it.test { Solution2().kthSmallest(convertArrayToTreeNode(root), k) }
    }

}
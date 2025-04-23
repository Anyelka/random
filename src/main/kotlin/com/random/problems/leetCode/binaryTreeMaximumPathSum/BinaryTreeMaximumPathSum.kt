package com.random.problems.leetCode.binaryTreeMaximumPathSum

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf<Int?>(1,2,3) to 6,
        arrayOf(-10,9,20,null,null,15,7) to 42,
        arrayOf<Int?>(1,2,3,4,5) to 11,
        arrayOf<Int?>(1,2,3,null,null,4,5) to 12,
        arrayOf<Int?>(-3) to -3,
        arrayOf<Int?>(-2,-1) to -1,
        arrayOf<Int?>(2,-1,-2) to 2,
        arrayOf<Int?>(-1,2,3) to 4,
        arrayOf<Int?>(1,-2,3) to 4,
        arrayOf(-1,-2,10,-6,null,-3,-6) to 10
    )

    input.forEach {
        val array = it.first
        it.test { Solution1().convertAndFindMaxPathSum(array) }
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
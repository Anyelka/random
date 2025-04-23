package com.random.problems.leetCode.kthSmallestElementInBst

import com.random.util.convert
import com.random.util.test

fun main() {
    val input = listOf(
        (arrayOf(3,1,4,null,2) to 1) to 1,
        (arrayOf(5,3,6,2,4,null,null,1) to 3) to 3,
        //(arrayOf(10, -10, 2, 3, null, 9, -3, null, null, 1, 5) to 4) to 2
    )

    input.forEach {
        val (root, k) = it.first
        it.test { Solution1().kthSmallest(convert(root), k) }
    }

}
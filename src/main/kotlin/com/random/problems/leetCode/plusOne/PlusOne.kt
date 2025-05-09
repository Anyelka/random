package com.random.problems.leetCode.plusOne

import com.random.util.test

fun main() {
    val input = listOf(
        intArrayOf(1,2,3) to intArrayOf(1,2,4),
        intArrayOf(4,3,2,1) to intArrayOf(4,3,2,2),
        intArrayOf(9) to intArrayOf(1,0),
        intArrayOf(9,9,9,9,9,9,9,9) to intArrayOf(1,0,0,0,0,0,0,0,0)
    )

    input.forEach {
        val digits = it.first
        it.test { Solution1().plusOne(digits) }
    }
}
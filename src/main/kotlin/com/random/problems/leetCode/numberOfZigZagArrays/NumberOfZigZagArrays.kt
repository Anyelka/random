package com.random.problems.leetCode.numberOfZigZagArrays

import com.random.util.test

fun main() {
    val input = listOf(
        Triple(3,4,5) to 2,
        Triple(3,1,3) to 10,
        Triple(3,1,4) to 28,
        Triple(7,9,39) to 650716800,
        Triple(12, 42, 145) to 744816685,
        Triple(13, 46, 159) to 755332253
    )

    input.forEach {
        val (n, l, r) = it.first
        it.test { Solution1().zigZagArrays(n, l, r) }
    }
}
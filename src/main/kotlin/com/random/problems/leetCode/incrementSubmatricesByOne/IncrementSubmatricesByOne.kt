package com.random.problems.leetCode.incrementSubmatricesByOne

import com.random.util.test

fun main() {
    val input = listOf(
        (3 to arrayOf(intArrayOf(1,1,2,2),intArrayOf(0,0,1,1))) to arrayOf(intArrayOf(1,1,0),intArrayOf(1,2,1),intArrayOf(0,1,1)),
        (2 to arrayOf(intArrayOf(0,0,1,1))) to arrayOf(intArrayOf(1,1),intArrayOf(1,1))
    )

    input.forEach {
        val (n, queries) = it.first
        it.test { Solution1().rangeAddQueries(n, queries) }
    }
}
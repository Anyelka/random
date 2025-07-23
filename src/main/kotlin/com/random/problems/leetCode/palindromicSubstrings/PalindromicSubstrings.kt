package com.random.problems.leetCode.palindromicSubstrings

import com.random.util.test

fun main() {
    val input = listOf(
        "abc" to 3,
        "aaa" to 6
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().countSubstrings(s) }
    }
}
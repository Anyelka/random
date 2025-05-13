package com.random.problems.leetCode.validParentheses

import com.random.util.test

fun main() {
    val input = listOf(
        "()" to true,
        "()[]{}" to true,
        "(]" to false,
        "([])" to true,
        "]" to false
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().isValid(s) }
    }
}
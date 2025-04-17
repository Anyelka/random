package com.random.problems.leetCode.generateParentheses

import com.random.util.test
import com.random.util.testWithSet

fun main() {
    val input = listOf(
        3 to listOf("((()))","(()())","(())()","()(())","()()()"),
        1 to listOf("()"),
        2 to listOf("()()", "(())")
    )

    input.forEach {
        val n = it.first
        it.testWithSet { Solution1().generateParenthesis(n) }
    }
}
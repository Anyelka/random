package com.random.problems.leetCode.evaluateReversePolishNotation

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf("2","1","+","3","*") to 9,
        arrayOf("4","13","5","/","+") to 6,
        arrayOf("10","6","9","3","+","-11","*","/","*","17","+","5","+") to 22,
        arrayOf("2") to 2
    )

    input.forEach {
        val tokens = it.first
        it.test { Solution1().evalRPN(tokens) }
    }
}
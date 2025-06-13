package com.random.problems.leetCode.validParenthesesString

import com.random.util.test

fun main() {
    val input = listOf(
        "()" to true,
        "(*)" to true,
        "(*))" to true,
        "(*()" to true,
        "(*)(" to false,
        "(*)))" to false,
        "(()" to false,
        ")(" to false,
        "(*" to true,
        ")*" to false,
        "()()" to true,
        "(()**)()(())" to true,
        "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())" to false
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().checkValidString(s) }
    }

}
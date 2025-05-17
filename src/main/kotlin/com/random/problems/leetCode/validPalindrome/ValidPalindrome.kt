package com.random.problems.leetCode.validPalindrome

import com.random.util.test

fun main() {
    val input = listOf(
        "A man, a plan, a canal: Panama" to true,
        "race a car" to false,
        " " to true,
        ".," to true,
        "0P" to false
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().isPalindrome(s) }
    }

}
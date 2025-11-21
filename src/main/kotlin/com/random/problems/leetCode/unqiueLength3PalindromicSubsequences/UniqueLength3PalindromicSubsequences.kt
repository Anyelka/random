package com.random.problems.leetCode.unqiueLength3PalindromicSubsequences

import com.random.util.test

fun main() {
    val input = listOf(
        "aabca" to 3,
        "adc" to 0,
        "bbcbaba" to 4,
        longInput1(),
        longInput2(),
        longInput3(),
        longInput4(),
        longInput5()
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().countPalindromicSubsequence(s) }
    }
}
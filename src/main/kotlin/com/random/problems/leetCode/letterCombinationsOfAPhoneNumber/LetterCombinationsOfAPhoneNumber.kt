package com.random.problems.leetCode.letterCombinationsOfAPhoneNumber

import com.random.util.testWithSet

fun main() {
    val input = listOf(
        "23" to listOf("ad","ae","af","bd","be","bf","cd","ce","cf"),
        "" to emptyList<String>(),
        "2" to listOf("a","b","c")
    )

    input.forEach {
        val digits = it.first
        it.testWithSet { Solution1().letterCombinations(digits) }
    }

}
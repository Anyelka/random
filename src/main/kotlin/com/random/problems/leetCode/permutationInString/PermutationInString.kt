package com.random.problems.leetCode.permutationInString

import com.random.util.test

fun main() {
    val input = listOf(
        ("ab" to "eidbaooo") to true,
        ("ab" to "eidboaoo") to false,
        ("adc" to "dcda") to true,
        ("a" to "ab") to true
    )

    input.forEach {
        val (s1, s2) = it.first
        it.test { Solution1().checkInclusion(s1, s2) }
    }
}
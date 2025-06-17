package com.random.problems.leetCode.minimumWindowSubstring

import com.random.util.test

fun main() {
    val input = listOf(
        ("ADOBECODEBANC" to "ABC") to "BANC",
        ("a" to "a") to "a",
        ("a" to "aa") to "",
        ("ab" to "a") to "a",
        ("ADOBECODEBANC" to "ABCA") to "ADOBECODEBA",
    )

    input.forEach {
        val (s, t) = it.first
        it.test { Solution1().minWindow(s,t) }
    }
}
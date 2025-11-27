package com.random.problems.leetCode.regularExpressionMatching

import com.random.util.test

fun main() {
    val input = listOf(
        ("aa" to "a") to false,
        ("aa" to "a*") to true,
        ("ab" to ".*") to true,
        ("aab" to ".*a") to false,
        ("ahgsdavbyrterhzfa" to ".*") to true,
        ("aabcba" to "a.*b.") to true,
        ("aabcb" to "a.*b.") to false,
        ("aabcb" to "a.*b.*") to true,
        ("aab" to "c*a*b") to true,
        ("a" to "ab*") to true
    )

    input.forEach {
        val (s, p) = it.first
        it.test { Solution1().isMatch(s, p) }
    }
}
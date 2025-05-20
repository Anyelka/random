package com.random.problems.leetCode.longestCommonSubsequence

import com.random.util.test

fun main() {
    val input = listOf(
        ("abcde" to "ace") to 3,
        ("abc" to "abc") to 3,
        ("abc" to "def") to 0,
        ("abc" to "fghbdjc") to 2,
        ("oxcpqrsvwf" to "shmtulqrypy") to 2,
        ("ezupkr" to "ubmrapg") to 2,
        ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" to "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa") to 210
    )

    input.forEach {
        val (s1, s2) = it.first
        it.test { Solution1().longestCommonSubsequence(s1, s2) }
    }
}
package com.random.problems.leetCode.interleavingString

import com.random.util.test

fun main() {
    val input = listOf(
        Triple("aabcc", "dbbca", "aadbbcbcac") to true,
        Triple("aabcc", "dbbca", "aadbbbaccc") to false,
        Triple("", "", "") to true,
        Triple("aabcc", "dbbc", "aadbbcbc") to false,
        Triple("aaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa") to false,
    )

    input.forEach {
        val (s1, s2, s3) = it.first
        it.test { Solution1().isInterleave(s1, s2, s3) }
    }
}
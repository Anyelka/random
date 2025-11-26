package com.random.problems.leetCode.palindromePartitioning

import com.random.util.test

fun main() {
    val input = listOf(
        "aab" to setOf(listOf("a","a","b"),listOf("aa","b")),
        "a" to setOf(listOf("a")),
        "ababab" to setOf(listOf("a","b","a","b","a","b"),listOf("a","b","a","bab"),listOf("a","b","aba","b"),
            listOf("a","bab","a","b"),listOf("a","babab"),listOf("aba","b","a","b"),listOf("aba","bab"),listOf("ababa","b"))
    )

    input.forEach {
        val s = it.first
        it.test { Solution1().partition(s).toSet() }
    }
}
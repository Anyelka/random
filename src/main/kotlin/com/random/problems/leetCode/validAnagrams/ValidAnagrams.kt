package com.random.problems.leetCode.validAnagrams

import com.random.util.test

fun main() {
    val input = listOf(
        ("anagram" to "nagaram") to true,
        ("rat" to "car") to false,
        ("" to "") to true,
        ("a" to "a") to true
    )

    input.forEach {
        val (s,t) = it.first
        it.test { Solution1().isAnagram(s, t) }
    }

}
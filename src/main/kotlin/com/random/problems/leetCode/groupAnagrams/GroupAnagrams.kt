package com.random.problems.leetCode.groupAnagrams

import com.random.util.testWithSetOfSets

fun main() {
    val input = listOf(
        arrayOf("eat","tea","tan","ate","nat","bat") to listOf(listOf("bat"), listOf("nat","tan"), listOf("ate","eat","tea")),
        arrayOf("") to listOf(listOf("")),
        arrayOf("a") to listOf(listOf("a")),
    )

    input.forEach {
        val strs = it.first
        it.testWithSetOfSets { Solution1().groupAnagrams(strs) }
    }
}
package com.random.problems.leetCode.wordBreak

import com.random.util.test

fun main() {
    val input = listOf(
        ("leetcode" to listOf("leet","code")) to true,
        ("applepenapple" to listOf("apple","pen")) to true,
        ("catsandog" to listOf("cats","dog","sand","and","cat")) to false,
        ("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab" to listOf("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")) to false
    )


    input.forEach {
        val (s, wordDict) = it.first
        it.test { Solution1().wordBreak(s, wordDict) }
    }

}
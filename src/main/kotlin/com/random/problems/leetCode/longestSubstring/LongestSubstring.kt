package com.random.problems.leetCode.longestSubstring



fun main() {
    val strings = arrayOf("abcabcbb", "bbbbb", "pwwkew", " ", "", "au")
    strings.forEach {
        val result = V2().lengthOfLongestSubstring(it)
        println(result)
    }
}

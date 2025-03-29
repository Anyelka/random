package com.random.problems.leetCode.longestSubstring

import com.random.util.test


fun main() {
    val input = listOf(
        "abcabcbb" to 3,
        "bbbbb" to 1,
        "pwwkew" to 3,
        " " to 1,
        "" to 0,
        "au" to 2,
        "dvdf" to 3
    )

    input.forEach {
        val s = it.first
        it.test { V3().lengthOfLongestSubstring(s) }
    }
}

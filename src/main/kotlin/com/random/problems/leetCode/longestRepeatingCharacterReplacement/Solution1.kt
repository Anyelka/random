package com.random.problems.leetCode.longestRepeatingCharacterReplacement

class Solution1 {
    // 1. Sliding window by keeping track of each characters frequency and comparing the window length - max frequency to k
    //
    //      TC: O(n * 26)
    //      SC: O(26)
    fun characterReplacement(s: String, k: Int): Int {
        val charFrequency = mutableMapOf<Char, Int>()
        var maxLength = 0
        var l = 0
        var r = 0
        while (r < s.length) {
            charFrequency[s[r]] = (charFrequency[s[r]] ?: 0) + 1

            val maxFrequency = charFrequency.values.max()

            while ((r - l + 1) - maxFrequency > k) {
                charFrequency[s[l]] = charFrequency[s[l]]!! - 1
                l++
            }
            maxLength = maxOf(maxLength, r - l + 1)
            r++
        }
        return maxLength
    }
}
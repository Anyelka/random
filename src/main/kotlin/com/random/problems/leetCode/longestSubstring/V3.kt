package com.random.problems.leetCode.longestSubstring

import kotlin.math.max

class V3 {

    //      Time complexity:        O(n)
    //      Space complexity:       O(min(n,m)) = O(1) = O(26)
    fun lengthOfLongestSubstring(s: String): Int {
        val letters = mutableSetOf<Char>()
        var i = 0
        var j = 0
        var maxLength = 0

        while(j < s.length) {
            while(j < s.length && !letters.contains(s[j])) {
                letters.add(s[j])
                j++
            }
            maxLength = maxOf(maxLength, j - i)
            while(j < s.length && letters.contains(s[j])) {
                letters.remove(s[i])
                i++
            }
        }

        return maxLength
    }
}
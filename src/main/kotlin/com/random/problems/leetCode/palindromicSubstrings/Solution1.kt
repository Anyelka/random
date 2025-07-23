package com.random.problems.leetCode.palindromicSubstrings

class Solution1 {
    fun countSubstrings(s: String): Int {
        var count = 0

        fun count(left: Int, right: Int): Int {
            var l = left
            var r = right
            var currentCount = 0
            while(l >= 0 && r < s.length) {
                if(s[l] != s[r]) break
                currentCount++
                l--
                r++
            }
            return currentCount
        }

        for(i in s.indices) {
            count += count(i, i)
            count += count(i, i + 1)
        }

        return count
    }
}
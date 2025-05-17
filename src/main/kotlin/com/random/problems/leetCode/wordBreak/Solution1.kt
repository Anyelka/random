package com.random.problems.leetCode.wordBreak

class Solution1 {

    // 1. Memoization
    //  TC: O(n^2 * k)
    //  SC: O(n^2)
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val memo = Array(s.length + 1) { Array<Boolean?>( s.length + 1) { null }}
        memo[s.length][s.length] = true
        fun canBreak(firstIndex: Int, lastIndex: Int): Boolean {
            if (lastIndex > s.length) return false
            if (memo[firstIndex][lastIndex] != null) return memo[firstIndex][lastIndex]!!
            val isValidWord = wordDict.contains(s.substring(firstIndex, lastIndex)) && canBreak(lastIndex, lastIndex)
            memo[firstIndex][lastIndex] = isValidWord || canBreak(firstIndex, lastIndex + 1)
            return memo[firstIndex][lastIndex]!!
        }
        return canBreak(0, 0)
    }
}
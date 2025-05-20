package com.random.problems.leetCode.longestCommonSubsequence

class Solution1 {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        return longestCommonSubsequence1(text1, text2)
    }

    //  1. Memoization
    //  TC: O(n * m)
    //  SC: O(n * m)
    private fun longestCommonSubsequence1(text1: String, text2: String): Int {
        val memo = Array(text1.length) { Array<Int?>(text2.length) { null } }

        fun lcs(i: Int, j: Int, memo: Array<Array<Int?>>): Int {
            if (i > text1.lastIndex || j > text2.lastIndex) return 0
            memo[i][j]?.let { return it }

            // take:
            val max = if (text1[i] == text2[j]) {
                1 + lcs(i + 1, j + 1, memo)
            } else {
                0
            }

            // skip:
            val max1 = lcs(i + 1, j, memo)
            val max2 = lcs(i, j + 1, memo)
            return maxOf(max, max1, max2).also { memo[i][j] = it }
        }

        return lcs(0, 0, memo)
    }
}
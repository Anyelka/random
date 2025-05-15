package com.random.problems.leetCode.decodeWays

class Solution1 {
    fun numDecodings(s: String): Int {
        return numDecodingsMemo(s)
    }

    //  1. Memoization:
    //
    //      TC: O(n)
    //      SC: O(n)
    //
    private fun numDecodingsMemo(s: String): Int {
        val digits = s.toCharArray().map { it.digitToInt() }.toIntArray()

        fun numDecodings(i: Int, memo: IntArray): Int {
            if (i >= digits.size) return 1
            if (memo[i] != -1) return memo[i]

            val canTakeThis = digits[i] != 0
            val canTakeNext =
                canTakeThis && i != digits.lastIndex && digits[i] < 3 && !(digits[i] == 2 && digits[i + 1] > 6)

            memo[i] = 0
            if (canTakeThis) memo[i] += numDecodings(i + 1, memo)
            if (canTakeNext) memo[i] += numDecodings(i + 2, memo)
            return memo[i]
        }

        return numDecodings(0, IntArray(s.length) { -1 })
    }

}
package com.random.problems.leetCode.interleavingString

class Solution1 {
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        return isInterleave3(s1, s2, s3)
    }

    // 1. Recursive solution without caching
    private fun isInterleave1(s1: String, s2: String, s3: String): Boolean {
        fun isInterleave(i: Int, j: Int, k: Int, n: Int, m: Int, current: Int): Boolean {
            if (i == s1.length && j == s2.length && k == s3.length) return ((n - m) in -1..1)
            if ((i == s1.length && j == s2.length) || k == s3.length) return false

            val c1 = if (i == s1.length) '#' else s1[i]
            val c2 = if (j == s2.length) '#' else s2[j]
            val nextChar = s3[k]
            if (nextChar == c1 && nextChar == c2) {
                val nextN = if (current == 1) n else (n + 1)
                val nextM = if (current == 2) m else (m + 1)
                return isInterleave(i + 1, j, k + 1, nextN, m, 1) || isInterleave(i, j + 1, k + 1, n, nextM, 2)
            } else if (nextChar == c1) {
                val nextN = if (current == 1) n else (n + 1)
                return isInterleave(i + 1, j, k + 1, nextN, m, 1)
            } else if (nextChar == c2) {
                val nextM = if (current == 2) m else (m + 1)
                return isInterleave(i, j + 1, k + 1, n, nextM, 2)
            }

            return false
        }

        return isInterleave(0, 0, 0, 0, 0, 0)
    }

    // 2. DP
    //  TC: O(s1 * s2)
    //  SC: O(s1 * s2)
    private fun isInterleave2(s1: String, s2: String, s3: String): Boolean {
        if(s1.length + s2.length != s3.length) return false

        val dp = mutableMapOf<Triple<Int,Int,Int>, Boolean>()
        fun canInterleave(i: Int, j: Int, k: Int, n: Int, m: Int, current: Int): Boolean {
            if (i == s1.length && j == s2.length && k == s3.length) return ((n - m) in -1..1)
            if ((i == s1.length && j == s2.length) || k == s3.length) return false
            dp[Triple(i,j,k)]?.let { return it }

            val c1 = if (i == s1.length) '#' else s1[i]
            val c2 = if (j == s2.length) '#' else s2[j]
            val nextChar = s3[k]

            val nextN = if (current == 1) n else (n + 1)
            val nextM = if (current == 2) m else (m + 1)
            val canInterleave = ((nextChar == c1) && canInterleave(i + 1, j, k + 1, nextN, m, 1) ||
                    (nextChar == c2) && canInterleave(i, j + 1, k + 1, n, nextM, 2))
            dp[Triple(i,j,k)] = canInterleave
            return canInterleave
        }

        return canInterleave(0, 0, 0, 0, 0, 0)
    }

    private fun isInterleave3(s1: String, s2: String, s3: String): Boolean {
        if (s1.length + s2.length != s3.length) return false

        val dp = mutableMapOf<Triple<Int,Int,Int>, Boolean>()

        fun canInterleave(i: Int, j: Int, k: Int, n: Int, m: Int, current: Int): Boolean {
            if (i == s1.length && j == s2.length && k == s3.length) return (n - m) in -1..1
            if ((i == s1.length && j == s2.length) || k == s3.length) return false

            val key = Triple(i, j, k)
            dp[key]?.let { return it }

            val nextChar = s3[k]
            var result = false

            if (i < s1.length && s1[i] == nextChar) {
                val nextN = if (current == 1) n else n + 1
                result = result || canInterleave(i + 1, j, k + 1, nextN, m, 1)
            }

            if (!result && j < s2.length && s2[j] == nextChar) {
                val nextM = if (current == 2) m else m + 1
                result = result || canInterleave(i, j + 1, k + 1, n, nextM, 2)
            }

            dp[key] = result
            return result
        }

        return canInterleave(0, 0, 0, 0, 0, 0)
    }

}
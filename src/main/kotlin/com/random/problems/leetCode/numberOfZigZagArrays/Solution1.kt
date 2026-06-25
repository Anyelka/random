package com.random.problems.leetCode.numberOfZigZagArrays

import com.random.util.pow

class Solution1 {
    fun zigZagArrays(n: Int, l: Int, r: Int): Int {
        return zigZagArrays2(n, l, r)
    }

    val modulo = (10.pow(9) + 7).toLong()

    // 1. Memoization
    fun zigZagArrays1(n: Int, l: Int, r: Int): Int {
        // 1. count all combinations of number between r-l
        // 2. add the restrictions:
        //  2.a no two adjacent elements
        //  2.b no 3 consecutive strictly increasing/decreasing elements

        val memo = mutableMapOf<Triple<Int,Int?,Int?>, Long>()

        fun allCombinations(remaining: Int, prev: Int? = null, prevPrev: Int? = null): Long {
            if(remaining == 0) return 1L
            val cacheKey = Triple(remaining, prev, prevPrev)
            if(memo.containsKey(cacheKey)) return memo[cacheKey]!!
            return (l..r).sumOf {
                if(it != prev && !strictlyIncreasingOrDecreasing(prevPrev, prev, it))
                    allCombinations(remaining - 1, it, prev)
                else 0L
            }.also { memo[cacheKey] = it % modulo }
        }

        return (allCombinations(n) % modulo).toInt()
    }

    private fun strictlyIncreasingOrDecreasing(num1: Int?, num2: Int?, num3: Int): Boolean {
        if(num1 == null || num2 == null) return false
        return (num1 < num2) == (num2 < num3)
    }


    // 2. Bottom-up DP with prefix-sum optimization
    fun zigZagArrays2(n: Int, l: Int, r: Int): Int {
        val m = r - l
        val dp = Array(n) { LongArray(m + 1) { 0 } }

        for(i in 0..m) {
            dp[0][i] = 1
        }

        for(i in 1..<n) {
            var prefix = 0L
            if(i % 2 == 1) {
                for(j in 0..m) {
                    dp[i][j] = prefix
                    prefix = (prefix + dp[i-1][j]) % modulo
                }
            } else {
                for(j in m downTo 0) {
                    dp[i][j] = prefix
                    prefix = (prefix + dp[i-1][j]) % modulo
                }
            }
        }

        return ((dp[n-1].sum() * 2) % modulo).toInt()
    }

}
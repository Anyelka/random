package com.random.problems.leetCode.numberOfZigZagArrays

import com.random.util.pow
import java.math.BigInteger

class Solution1 {
    fun zigZagArrays(n: Int, l: Int, r: Int): Int {
        return zigZagArrays1(n, l, r)
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

}
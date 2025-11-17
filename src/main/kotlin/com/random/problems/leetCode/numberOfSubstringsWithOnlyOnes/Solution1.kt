package com.random.problems.leetCode.numberOfSubstringsWithOnlyOnes

import com.random.util.pow
import kotlin.math.pow

class Solution1 {
    // TC: O(n)
    fun numSub(s: String): Int {
        val dp = mutableMapOf<Int, Long>().also { it[1] = 1L }
        fun oneCountInSubarray(length: Int): Long {
            if(dp.containsKey(length)) return dp[length]!!
            for(i in 2..length) {
                dp[i] = dp[i-1]!! + i
            }
            return dp[length]!!
        }

        var result = 0L
        var prevValue = 'a'
        var subarrayLength = 0
        for(i in 0..s.length) {
            val value = if(i < s.length) s[i] else 'a'
            if(value == prevValue) {
                subarrayLength++
            } else {
                if(prevValue == '1') result += oneCountInSubarray(subarrayLength)
                prevValue = value
                subarrayLength = 1
            }
        }
        return (result % (10.0.pow(9.0) + 7)).toInt()
    }
}
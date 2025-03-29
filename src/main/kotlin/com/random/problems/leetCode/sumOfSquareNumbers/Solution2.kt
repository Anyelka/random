package com.random.problems.leetCode.sumOfSquareNumbers

import kotlin.math.sqrt

class Solution2 {
    fun judgeSquareSum(c: Int): Boolean {
        var left = 0L
        var right = sqrt(c.toDouble()).toLong()

        while(left <= right) {
            if(left * left + right * right == c.toLong()) return true
            else if(left * left + right * right < c) left++
            else right--
        }
        return false
    }
}
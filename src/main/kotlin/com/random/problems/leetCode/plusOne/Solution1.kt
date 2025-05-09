package com.random.problems.leetCode.plusOne

class Solution1 {
    fun plusOne(digits: IntArray): IntArray {
        val result = digits.clone()
        var hasRemainder = true
        var i = digits.size - 1
        while(hasRemainder) {
            if(i == -1) return intArrayOf(1) + result
            val nextDigit = result[i] + 1
            if(nextDigit == 10) {
                result[i] = 0
                i--
            } else {
                result[i] = nextDigit
                hasRemainder = false
            }

        }
        return result
    }
}
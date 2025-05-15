package com.random.problems.leetCode.reverseInteger

class Solution3 {
    fun reverse(x: Int): Int {
        return reverseLoop(x)
    }

    private fun reverseLoop(x: Int): Int {
        var result = 0
        var number = x
        while (number != 0) {
            if (result > Int.MAX_VALUE / 10 || result < Int.MIN_VALUE / 10) return 0
            result = result * 10 + number % 10
            number /= 10
        }
        return result
    }

}
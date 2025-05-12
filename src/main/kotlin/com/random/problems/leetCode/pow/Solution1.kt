package com.random.problems.leetCode.pow

import kotlin.math.abs

class Solution1 {
    fun myPow(x: Double, n: Int): Double {
        return myPow2(x, n)
    }

    //  1.Solution: loop
    //      TC: O(n)
    //      SC: O(1)
    private fun myPow1(x: Double, n: Int): Double {
        var result = 1.0
        var exp = abs(n)
        while (exp > 0) {
            result *= x
            exp--
        }
        return if (n > 0) result else 1 / result
    }

    //  2. Solution: Divide and Conquer
    //      always split n in half
    //      cache the result in memo because of duplicate method calls
    //
    //      TC: O(log(n))
    //      SC: O(log(n))
    //
    private fun myPow2(x: Double, n: Int): Double {
        return myPow2(x, n.toLong(), mutableMapOf())
    }

    private fun myPow2(x: Double, n: Long, memo: MutableMap<Double, Double>): Double {
        if (memo.containsKey(x)) return memo[x]!!
        if (n == 0L) return 1.0
        if (n < 0) return myPow2(1 / x, -n, memo)
        return (if (n % 2 == 0L) myPow2(x, n / 2, memo) * myPow2(x, n / 2, memo)
        else myPow2(x, n / 2, memo) * x * myPow2(x, n / 2, memo))
            .also { memo[x] = it }
    }

    private fun myPow3(x: Double, n: Int): Double {
        return myPow3(x, n.toLong())
    }

    //  2. Solution: Divide and Conquer without memo
    //      always split n in half
    //
    //      TC: O(log(n))
    //      SC: O(log(n))
    //
    private fun myPow3(x: Double, n: Long): Double {
        if (n == 0L) return 1.0
        if (n < 0) return myPow3(1 / x, -n)
        return if (n % 2 == 0L) myPow3(x * x, n / 2)
        else x * myPow3(x * x, n / 2)
    }
}
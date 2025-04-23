package com.random.problems.leetCode.sumOfTwoIntegers

class Solution1 {
    fun getSum(a: Int, b: Int): Int {
        return getSumRecursive(a, b)
    }

    private fun getSumLoop(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            //  calculate the carry
            val temp = (x and y).shl(1)
            x = x xor y
            y = temp
        }
        return x
    }

    private fun getSumRecursive(a: Int, b: Int): Int {
        return if(b == 0) a else getSumRecursive(a xor b, (a and b).shl(1))
    }
}
package com.random.problems.leetCode.countingBits

class Solution1 {
    fun countBits(n: Int): IntArray {
        return countBits2(n)
    }

    //  TC: O(n * log(n))
    //  SC: O(n)
    private fun countBits1(n: Int): IntArray {
        val result = IntArray(n + 1)
        for (i in 0..n) {
            var count = 0
            var number = i
            while (number != 0) {
                count += number and 1
                number = number.shr(1)
            }
            result[i] = count
        }
        return result
    }

    //  0   ->          0
    //  1   ->          1
    //  2   ->          10
    //  3   ->          11
    //  4   ->          100
    //  5   ->          101
    //  6   ->          110
    //  7   ->          111
    //  8   ->          1000
    //  9   ->          1001
    //  10  ->          1010
    //  11  ->          1011
    //  12  ->          1100
    //  13  ->          1101
    //  14  ->          1110
    //  15  ->          1111
    //  16  ->          10000
    //  ...        ...14 numbers...
    //  31  ->          11111
    //  32  ->          100000
    private fun countBits2(n: Int): IntArray {
        val result = IntArray(n + 1) { 0 }
        (1..n).forEach { result[it]  = result[it shr 1] + (it and 1) }
        return result
    }

}
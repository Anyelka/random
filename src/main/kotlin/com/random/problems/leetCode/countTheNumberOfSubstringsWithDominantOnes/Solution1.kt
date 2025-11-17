package com.random.problems.leetCode.countTheNumberOfSubstringsWithDominantOnes

class Solution1 {
    fun numberOfSubstrings(s: String): Int {
        return numberOfSubstrings2(s)
    }

    fun numberOfSubstrings1(s: String): Int {
        // brute force:
        //  - go through each substring:
        //      -> iterate over i and j in 2 nested loops
        //          -> we sum the 1s and 0s between i and j in a 3rd nested loop
        //          -> if the condition holds, we add 1 to the result
        //  TC: O(n ^ 3)

        // TC: O(n ^ 2)

        // TOO SLOW

        var result = 0
        for(i in 0..s.lastIndex) {
            var zeros = 0
            var ones = 0
            for(j in i..s.lastIndex) {
                if(s[j] == '0') zeros++
                if(s[j] == '1') ones++
                if(ones >= zeros * zeros) {
                    result++
                }
            }
        }

        return result
    }

    //
    //  TC: O(n * sqrt(n))
    //  SC: O(n)
    fun numberOfSubstrings2(s: String): Int {
        val n = s.length
        val nextZeros = IntArray(n)
        var prevZero = n
        for(i in s.lastIndex downTo 0) {
            nextZeros[i] = prevZero
            if(s[i] == '0') {
                prevZero = i
            }
        }

        var result = 0

        for(l in 0 until n) {
            var zeroes = if(s[l] == '0') 1 else 0
            var r = l

            while(zeroes * zeroes <= n) {
                val nextZero = if(r < n) nextZeros[r] else n
                val ones = (nextZero - l) - zeroes
                if(ones >= zeroes * zeroes) {
                    result += minOf(nextZero - r, ones - zeroes * zeroes + 1)
                }

                r = nextZero
                zeroes += 1

                if(r == n) break
            }
        }

        return result
    }
}
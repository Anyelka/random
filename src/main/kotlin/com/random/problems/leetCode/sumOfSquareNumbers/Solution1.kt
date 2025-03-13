package com.random.problems.leetCode.sumOfSquareNumbers

import com.random.util.pow
import com.random.util.sqrt
import kotlin.math.sqrt

class Solution1 {
    fun judgeSquareSum(c: Int): Boolean {
        return judgeSquareSumOneLoop(c)
    }

    // Brute Force solution:
    //      go through all possible combination of numbers from 0 to the actual number
    private fun judgeSquareSumBruteForce(c: Int): Boolean {
        for(i in 0.. c) {
            for(j in 0.. c) {
                if(i.pow(2) + j.pow(2) == c) {
                    return true
                }
            }
        }
        return false
    }

    // Brute Force solution optimized:
    //      go through all possible combination of numbers from 0 to SQUARE ROOT of the number
    private fun judgeSquareSumBruteForceUntilSqrt(c: Int): Boolean {
        for(i in 0.. c.sqrt()) {
            for(j in 0.. c.sqrt()) {
                if(i.pow(2) + j.pow(2) == c) {
                    return true
                }
            }
        }
        return false
    }

    // Set solution:
    //      go through each item once from 0 to SQUARE ROOT of the number
    //          store the square of each number in the set
    //          if the remainder = c - square is already in the set, the square of another number equals c - square
    private fun judgeSquareSumSet(c: Int): Boolean {
        val squares = mutableSetOf<Int>()
        for(i in 0.. c.sqrt()) {
            val square = i.pow(2)
            squares.add(square)
            if(squares.contains(c - square)) return true
        }
        return false
    }

    // One Loop solution without set:
    private fun judgeSquareSumOneLoop(c: Int): Boolean {
        for(i in 0.. c.sqrt()) {
            val square = i.pow(2)
            val remSqrt = sqrt((c - square).toDouble())
            if(remSqrt == remSqrt.toInt().toDouble()) return true
        }
        return false
    }

    // Possibly a two pointer solution ???
}
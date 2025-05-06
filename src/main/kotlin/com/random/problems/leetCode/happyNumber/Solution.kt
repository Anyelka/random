package com.random.problems.leetCode.happyNumber

import com.random.util.toDigits

class Solution {
    fun isHappy(n: Int): Boolean {
        return isHappy1(n)
    }

    //  1. Solution
    //      - keep track of visited numbers and detect cycle
    //  TC: O(?)
    private fun isHappy1(n: Int): Boolean {
        val visited = mutableSetOf<Int>()
        var current = n

        fun next(current: Int) = current.toString().sumOf { it.digitToInt() * it.digitToInt() }

        while (current != 1) {
            if (!visited.add(current)) return false
            current = next(current)
        }
        return true
    }
}
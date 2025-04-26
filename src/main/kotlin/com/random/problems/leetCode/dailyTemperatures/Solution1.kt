package com.random.problems.leetCode.dailyTemperatures

import java.util.Stack

class Solution1 {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        return dailyTemperatures3(temperatures)
    }

    // Solution 1: Double loop
    //  TC: O(n^2)
    //  SC: O(1)
    private fun dailyTemperatures1(temperatures: IntArray): IntArray {
        fun getNumberOfDays(i: Int, temp: Int): Int {
            var days = 0
            var j = i + 1
            while (j < temperatures.size) {
                days++
                if (temperatures[j++] > temp) return days
            }
            return 0
        }
        return temperatures.withIndex().map { (i, temp) -> getNumberOfDays(i, temp) }.toIntArray()
    }

    //  Solution 2: Two pointers:
    //  TC: O(n^2)
    //  SC: O(n)
    private fun dailyTemperatures2(temperatures: IntArray): IntArray {
        val result = IntArray(temperatures.size) { 0 }
        var l = 0
        var r = 1

        while(l < temperatures.size) {
            if(r > temperatures.size - 1) {
                l++
                r = l + 1
                continue
            }
            if(temperatures[r] > temperatures[l]) {
                result[l] = r - l
                l++
                r = l + 1
            } else {
                r++
            }
        }
        return result
    }

    // Solution 3: Stack
    //  TC: O(n)
    //  SC: O(n)
    private fun dailyTemperatures3(temperatures: IntArray): IntArray {
        val result = IntArray(temperatures.size) { 0 }
        val stack = Stack<Pair<Int,Int>>()
        for((index, temp) in temperatures.withIndex()) {
            while(stack.isNotEmpty() && temp > stack.last().first) {
                val nextIndex = stack.pop().second
                result[nextIndex] = index - nextIndex
            }
            stack.push(temp to index)
        }
        return result
    }
}
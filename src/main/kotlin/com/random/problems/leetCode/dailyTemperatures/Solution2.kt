package com.random.problems.leetCode.dailyTemperatures

import java.util.*

class Solution2 {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        return dailyTemperatures2(temperatures)
    }

    // 1. Two pointers:
    //  TC: O(n ^ 2)
    //  SC: O(n)
    private fun dailyTemperatures1(temperatures: IntArray): IntArray {
        var l = 0
        var r = 0
        val result = IntArray(temperatures.size) { 0 }
        while (l < temperatures.lastIndex) {
            r = l + 1
            while (r <= temperatures.lastIndex && temperatures[r] <= temperatures[l]) r++
            result[l] = if (r > temperatures.lastIndex) 0 else r - l
            l++
        }
        return result
    }

    // 2. Stack:
    //  TC: O(n)
    //  SC: O(n)
    private fun dailyTemperatures2(temperatures: IntArray): IntArray {
        val stack = Stack<Pair<Int,Int>>()
        val result = IntArray(temperatures.size)
        for((i,temp) in temperatures.withIndex()) {
            while(stack.isNotEmpty() && stack.peek().first < temp) {
                val (prevTemp, prevIndex) = stack.pop()
                result[prevIndex] = i - prevIndex
            }
            stack.push(temp to i)
        }
        return result
    }
}
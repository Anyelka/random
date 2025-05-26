package com.random.problems.leetCode.carFleet

import java.util.*

class Solution1 {

    //  1. Stack with Time-to-Finish values as double
    //  TC: O(n * log(n))
    //  SC: O(n)
    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        val cars = position.withIndex().map { (i, pos) -> pos to speed[i] }.sortedByDescending { it.first }
        val stack = Stack<Double>()

        for(car in cars) {
            val timeToFinish = (target.toDouble() - car.first) / car.second
            if(stack.isEmpty() || timeToFinish > stack.peek()) stack.push(timeToFinish)
        }

        return stack.size
    }
}
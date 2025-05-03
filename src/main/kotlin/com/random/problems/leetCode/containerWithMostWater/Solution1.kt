package com.random.problems.leetCode.containerWithMostWater

class Solution1 {
    fun maxArea(height: IntArray): Int {
        return maxArea2(height)
    }

    //  1. Naive double loop solution
    //      TC: O(n^2)
    //      SC: O(1)
    private fun maxArea1(height: IntArray): Int {
        var maxWater = 0
        for(i in height.indices) {
            for(j in i+1..<height.size) {
                maxWater = maxOf(maxWater, minOf(height[i],height[j]) * (j - i))
            }
        }
        return maxWater
    }

    // 2. Two pointer solution:
    //  always shift the pointer with the smaller height
    //  TC: O(n)
    //  SC: O(1)
    private fun maxArea2(height: IntArray): Int {
        var l = 0
        var r = height.size - 1
        var maxWater = 0
        while(l < r) {
            maxWater = maxOf(maxWater, minOf(height[l],height[r]) * (r-l))
            if(height[l] > height[r]) {
                r--
            } else {
                l++
            }
        }
        return maxWater
    }
}
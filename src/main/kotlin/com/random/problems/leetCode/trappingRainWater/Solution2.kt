package com.random.problems.leetCode.trappingRainWater

class Solution2 {
    fun trap(height: IntArray): Int {
        return trap2(height)
    }

    // Solution1:
    //      TC:     O(n)
    //      SC:     O(n)
    //
    private fun trap1(height: IntArray): Int {
        val maxHeightLeft = IntArray(height.size) { 0 }
        val maxHeightRight = IntArray(height.size) { 0 }
        for (i in 1..<height.size) {
            maxHeightLeft[i] = maxOf(height[i - 1], maxHeightLeft[i - 1])
            maxHeightRight[height.size - 1 - i] = maxOf(height[height.size - i], maxHeightRight[height.size - i])
        }

        fun Int.trappableAtIndex() =
            (minOf(maxHeightLeft[this], maxHeightRight[this]) - height[this]).coerceAtLeast(0)

        return height.indices.sumOf { it.trappableAtIndex() }
    }

    fun trap2(height: IntArray): Int {
        var maxLeft = 0
        var maxRight = 0
        var left = 0
        var right = height.size - 1
        var totalWater = 0
        while(left < right) {
            maxLeft = maxOf(maxLeft, height[left])
            maxRight = maxOf(maxRight, height[right])
            if(maxLeft < maxRight) {
                totalWater += (maxLeft - height[left]).coerceAtLeast(0)
                left++
            } else {
                totalWater += (maxRight - height[right]).coerceAtLeast(0)
                right--
            }
        }
        return totalWater
    }
}

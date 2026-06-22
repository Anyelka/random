package com.random.problems.leetCode.maximumBuildingHeight

import kotlin.math.abs

class Solution1 {
    fun maxBuilding(n: Int, restrictions: Array<IntArray>): Int {
        return maxBuilding2(n, restrictions)
    }

    // Solution1: go over intervals one-by-one and find the highest point between start and end:
    //      problem: if we only consider each interval 1-by-1 we cant really restrict the max height
    //          by any restriction that is coming later in the indices

    fun maxBuilding1(n: Int, restrictions: Array<IntArray>): Int {
        fun getMaxInnerHeightAndEndHeight(start: Int, end: Int, startMax: Int, endMax: Int): Pair<Int, Int> {
            val maxHeightGain = end - start
            val maxEndHeight = startMax + maxHeightGain
            var maxInnerHeight: Int
            if (endMax >= maxEndHeight) {
                maxInnerHeight = maxEndHeight
            } else {
                maxInnerHeight = maxOf(startMax, endMax) + ((maxHeightGain - abs(endMax - startMax)) / 2)
            }
            return maxInnerHeight to maxEndHeight
        }

        val sortedRestrictions = restrictions.sortedBy { it[0] }

        var start = 1
        var startMax = 0
        var end = n
        var endMax = Int.MAX_VALUE
        var totalMaxHeight = 0
        for (restriction in sortedRestrictions) {
            // 1. update the end coordinates for the current interval
            end = restriction[0]
            endMax = restriction[1]
            // 2. calculate the max height in the current interval
            val (maxInnerHeight, maxEndHeight) = getMaxInnerHeightAndEndHeight(start, end, startMax, endMax)
            totalMaxHeight = maxOf(totalMaxHeight, maxInnerHeight)
            // 3. set the start coordinates for the next interval
            start = end
            startMax = minOf(endMax, maxEndHeight)
        }

        val (maxInnerHeight, maxEndHeight) = getMaxInnerHeightAndEndHeight(start, n, startMax, Int.MAX_VALUE)
        totalMaxHeight = maxOf(totalMaxHeight, maxInnerHeight)

        return totalMaxHeight
    }

    fun maxBuilding2(n: Int, restrictions: Array<IntArray>): Int {
        var sortedRestrictions =
            listOf(intArrayOf(1,0)) + restrictions.sortedBy { it[0] }
        if(sortedRestrictions.last()[0] != n) {
            sortedRestrictions = sortedRestrictions + listOf(intArrayOf(n, n-1))
        }

        for(i in 1..sortedRestrictions.lastIndex) {
            val (prevIndex, prevMax) = sortedRestrictions[i-1]
            val (currentIndex, currentMax) = sortedRestrictions[i]
            val restrictedCurrentMax = minOf(currentMax, prevMax + (currentIndex - prevIndex))
            sortedRestrictions[i][1] = restrictedCurrentMax
        }

        for(j in sortedRestrictions.lastIndex-1  downTo 1) {
            val (prevIndex, prevMax) = sortedRestrictions[j+1]
            val (currentIndex, currentMax) = sortedRestrictions[j]
            val restrictedCurrentMax = minOf(currentMax, prevMax + (prevIndex - currentIndex))
            sortedRestrictions[j][1] = restrictedCurrentMax
        }

        var totalMax = 0
        for(i in 1..sortedRestrictions.lastIndex) {
            val (prevIndex, prevMax) = sortedRestrictions[i-1]
            val (currentIndex, currentMax) = sortedRestrictions[i]
            val maxPeak = (currentIndex - prevIndex + prevMax + currentMax) / 2
            totalMax = maxOf(totalMax, maxPeak)
        }

        return totalMax
    }

}
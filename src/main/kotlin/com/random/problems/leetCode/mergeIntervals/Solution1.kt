package com.random.problems.leetCode.mergeIntervals

class Solution1 {
    //      TC: O(i * log(i) + i)
    //      SC: O(
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }

        val result = mutableListOf<IntArray>()
        var lastStart = intervals[0][0]
        var lastEnd = -1

        for(interval in intervals) {
            if(lastEnd >= 0 && interval[0] > lastEnd) {
                result.add(intArrayOf(lastStart, lastEnd))
                lastStart = interval[0]
            }
            lastEnd = maxOf(lastEnd, interval[1])
        }
        result.add(intArrayOf(lastStart, lastEnd))
        return result.toTypedArray()
    }
}
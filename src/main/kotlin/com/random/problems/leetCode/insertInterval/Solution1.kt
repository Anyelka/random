package com.random.problems.leetCode.insertInterval

class Solution1 {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        return insert2(intervals, newInterval)
    }

    fun insert1(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val result = mutableListOf<IntArray>()

        var i = 0
        // 1. handle all values before newInterval
        while(i < intervals.size && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i])
            i++
        }

        // 2. handle values merging with newInterval
        var start = newInterval[0]
        if(i < intervals.size) start = minOf(start, intervals[i][0])
        // we should find the min and max until the intervals overlap with newInterval
        while(i < intervals.size && intervals[i][0] <= newInterval[1]) {
            i++
        }
        var end = newInterval[1]
        if(i > 0) end = maxOf(end, intervals[i-1][1])
        result.add(intArrayOf(start, end))

        // 3. handle values after newInterval
        while(i < intervals.size) {
            result.add(intervals[i])
            i++
        }

        return result.toTypedArray()
    }

    fun insert2(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val result = mutableListOf<IntArray>()

        var added = false

        var (start, end) = newInterval
        for(interval in intervals) {
            when {
                interval[1] < start -> result.add(interval)
                interval[0] > end -> {
                    if(!added) {
                        result.add(intArrayOf(start, end))
                        added = true
                    }
                    result.add(interval)
                }
                else -> {
                    start = minOf(start, interval[0])
                    end = maxOf(end, interval[1])
                }
            }
        }

        if(!added) result.add(intArrayOf(start, end))

        return result.toTypedArray()
    }
}
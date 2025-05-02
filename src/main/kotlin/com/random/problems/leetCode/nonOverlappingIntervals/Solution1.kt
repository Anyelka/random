package com.random.problems.leetCode.nonOverlappingIntervals

class Solution1 {
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        return eraseOverlapIntervals2(intervals)
    }

    //  1. Recursive solution
    //  VERY SLOW
    //      - solves each subproblem multiple times
    //
    //  TC: O(n!)
    //  SC: O(n^2)

    private fun eraseOverlapIntervals1(intervals: Array<IntArray>): Int {
        if (!intervals.hasOverlap()) return 0
        if (intervals.size <= 1) return 0
        return intervals.withIndex().minOf { (i, it) ->
            val nextIntervals = intervals.toMutableList()
            nextIntervals.removeAt(i)
            1 + eraseOverlapIntervals(nextIntervals.toTypedArray())
        }
    }

    private fun Array<IntArray>.hasOverlap(): Boolean {
        val covered = mutableSetOf<Int>()
        for (interval in this) {
            for (i in interval[0]..<interval[1]) {
                if (!covered.add(i)) return true
            }
        }
        return false
    }

    // 2. Greedy approach after sorting the intervals by end date
    //  TC: O(n*log(n))
    //  SC: O(1)
    private fun eraseOverlapIntervals2(intervals: Array<IntArray>): Int {
        if (intervals.size <= 1) return 0

        intervals.sortBy { it[1] }

        var end = intervals[0][1]
        var count = 0

        for (i in 1 until intervals.size) {
            if (intervals[i][0] < end) {
                count++
            } else {
                end = intervals[i][1]
            }
        }

        return count
    }

}
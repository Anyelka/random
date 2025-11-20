package com.random.problems.leetCode.setIntersectionSizeAtLeastTwo

class Solution1 {
    // Sorting the intervals first by end ascending, then by start descending and
    //      calculate the next sections greedily
    //
    //      TC: O(n * log(n))
    //      SC: O(n)
    //
    fun intersectionSizeTwo(intervals: Array<IntArray>): Int {
        val sortedIntervals = intervals.sortedWith(compareBy<IntArray>{ it[1] }.thenByDescending { it[0] })
        var p1 = -1
        var p2 = -1
        var result = 0
        for((start, end) in sortedIntervals) {
            if(start > p2) {
                result += 2
                p2 = end
                p1 = end - 1
            } else if(start > p1) {
                result += 1
                val temp = p2
                p2 = end
                p1 = temp
            }
        }
        return result
    }
}
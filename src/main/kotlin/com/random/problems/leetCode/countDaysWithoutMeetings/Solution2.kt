package com.random.problems.leetCode.countDaysWithoutMeetings

class Solution2 {
    //  TC: O(n * log(n)) + O(m)    m = meetings size
    //  SC: O(1)
    fun countDays(days: Int, meetings: Array<IntArray>): Int {
        meetings.sortBy { it[0] }
        var freeDays = (meetings[0][0] - 1).coerceAtLeast(0)
        var maxMeetingEnd = meetings[0][1]
        for(i in meetings.indices) {
            maxMeetingEnd = maxOf(maxMeetingEnd, meetings[i][1])
            val dayLimit = if(i == meetings.size - 1) days + 1 else meetings[i + 1][0]
            freeDays += (dayLimit - maxMeetingEnd - 1).coerceAtLeast(0)
        }
        return freeDays
    }
}
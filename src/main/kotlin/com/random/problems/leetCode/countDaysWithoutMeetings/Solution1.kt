package com.random.problems.leetCode.countDaysWithoutMeetings

class Solution1 {
    fun countDays(days: Int, meetings: Array<IntArray>): Int {
        return countDays1F(meetings, days)
    }

    /*
    * Naive solution:
    *   go through each meeting days array and add them
    *
    *       Time complexity:    O(n * d)
    *       Space complexity:   O(d)
    *
    *           where   n = number of meetings
    *                   d = number of total days
    *
    * */
    private fun countDays1(meetings: Array<IntArray>, days: Int): Int {
        val daysWithMeeting = mutableSetOf<Int>()
        for (meeting in meetings) {
            for (dayOfMeeting in meeting[0]..meeting[1]) {
                daysWithMeeting.add(dayOfMeeting)
            }
        }
        return days - daysWithMeeting.size
    }

    // Functional style :
    private fun countDays1F(meetings: Array<IntArray>, days: Int) =
        days - meetings.flatMap { it[0]..it[1] }.distinct().count()

    /*
    * Solution 2:
    *   - sort the meetings by start day
    *   - iterate over all days while also keeping the track of the current meeting
    *       - if the current day is not in the current meeting period, we increment the result
    *       - if the current day is past the end date of the current meeting, we go on to the next meeting
    *
    *       Time complexity:    O(n * log(n) + d)
    *       Space complexity:   O(1)
    *
    * */
    private fun countDays2(meetings: Array<IntArray>, days: Int): Int {
        meetings.sortBy { it[0] }
        var result = 0
        var m = 0
        for (i in 1..days) {
            while (m < meetings.size && i > meetings[m][1])
                m++
            if (m >= meetings.size || i < meetings[m][0] || i > meetings[m][1])
                result++
        }
        return result
    }

}
package com.random.problems.leetCode.meetingRooms

import com.random.problems.meetingRooms.Solution1
import com.random.util.Interval
import com.random.util.test

fun main() {
    val input = listOf(
        listOf(Interval(0,30),Interval(5,10),Interval(15,20)) to false,
        listOf(Interval(5,8),Interval(9,15)) to true
    )

    input.forEach {
        val intervals = it.first
        it.test { Solution1().canAttendMeetings(intervals) }
    }
}
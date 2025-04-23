package com.random.problems.leetCode.countDaysWithoutMeetings

import com.random.util.test

fun main() {
    val input = listOf(
        (arrayOf(intArrayOf(5,7), intArrayOf(1,3), intArrayOf(9,10)) to 10) to 2,
        (arrayOf(intArrayOf(2,4), intArrayOf(1,3)) to 5) to 1,
        (arrayOf(intArrayOf(1,6)) to 6) to 0,
        (arrayOf(intArrayOf(3,5)) to 10) to 7,
        (arrayOf(intArrayOf(6,7), intArrayOf(9,10), intArrayOf(4, 14)) to 15) to 4,
        TestInput.get()
    )

    input.forEach {
        val (meetings, days) = it.first
        it.test { Solution2().countDays(days, meetings)}
    }
}
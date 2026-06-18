package com.random.problems.leetCode.angleBetweenHandsOfClock

class Solution1 {
    fun angleClock(hour: Int, minutes: Int): Double {
        val hoursDegrees = (180/360.toDouble()) * (hour*60+minutes).toDouble()
        val minutesDegrees = (180/30).toDouble() * minutes.toDouble()
        val (bigger, smaller) = if(hoursDegrees > minutesDegrees) hoursDegrees to minutesDegrees else minutesDegrees to hoursDegrees
        return minOf(bigger-smaller, smaller + 360-bigger)
    }
}
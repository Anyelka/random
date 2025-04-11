package com.random.problems.leetCode.countPowerfulIntegers

import com.random.util.pow
import com.random.util.toDigits
import kotlin.math.pow

class Solution1 {
    fun numberOfPowerfulInt(start: Long, finish: Long, limit: Int, s: String): Long {
        return numberOfPowerfulInt2(s, start, finish, limit)
    }

    // 1. Solution
    private fun numberOfPowerfulInt1(s: String, start: Long, finish: Long, limit: Int): Long {
        var count = 0L
        val base = 10.0.pow(s.length).toInt()
        val numbers = mutableSetOf<Long>()
        val firstPotential = start / base
        val firstRemainder = start % base
        val first = if (firstRemainder > s.toInt()) firstPotential + 1 else firstPotential
        val lastPotential = finish / base
        val lastRemainder = finish % base
        val last = if (lastRemainder < s.toInt()) lastPotential - 1 else lastPotential


        for (i in first..last) {
            if (i.allDigitsMax(limit)) {
                count++
                numbers.add(i)
            }
        }
        return count
    }

    private fun numberOfPowerfulInt2(s: String, start: Long, finish: Long, limit: Int): Long {
        val base = 10.0.pow(s.length).toInt()
        val firstPotential = start / base
        val firstRemainder = start % base
        val first = if (firstRemainder > s.toInt()) firstPotential + 1 else firstPotential
        val lastPotential = finish / base
        val lastRemainder = finish % base
        val last = if (lastRemainder < s.toInt()) lastPotential - 1 else lastPotential

        // shift first to 0
        /*val shiftedFirst = 0
        val shiftedLast = last - first*/

        //

        return countPowerfulIntegersBelow(last, limit) - countPowerfulIntegersBelow(first, limit)
    }

    private fun countPowerfulIntegersBelow(last: Long, limit: Int): Long {
        if(last < 0) return 0L

        var count = 0L
        val lastDigits = last.toDigits()
        val lastLength = lastDigits.size

        for (digitIndex in 0..<lastLength) {
            val digitValue = lastDigits[digitIndex]
            //val digitMax = minOf(digitValue, limit)
            val length = lastLength - 1 - digitIndex
            if(limit < digitValue) {
                count += (limit + 1).pow(length)
                return count
            }

            count += digitValue * (limit + 1).pow(length)
        }
        return count
    }

    private fun Long.allDigitsMax(limit: Int): Boolean {
        return this.toDigits().all { it <= limit }
    }
}

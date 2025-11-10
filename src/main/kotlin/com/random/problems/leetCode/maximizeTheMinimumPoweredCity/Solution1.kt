package com.random.problems.leetCode.maximizeTheMinimumPoweredCity

class Solution1 {
    fun maxPower(stations: IntArray, r: Int, k: Int): Long {
        // 1. collect total power of cities
        val paddedStations = IntArray(r) { 0 } + stations + IntArray(r) { 0 }
        var left = 0
        var right = left
        val powers = IntArray(stations.size)
        var sum = 0

        while(right - left < 2 * r) {
            sum += paddedStations[right]
            right++
        }

        while(left < stations.size) {
            sum += paddedStations[right]
            powers[left] = sum
            sum -= paddedStations[left]
            left++
            right++
        }

        // 2. calculate


        var minValue = stations[0]
        var minIndexes = mutableListOf<Int>()
        var remainingSteps = k

        while(k > 0) {
            // 1. collect indexes of min value
            for((i, value) in stations.withIndex()) {
                if(value < minValue) {
                    minIndexes = mutableListOf(i)
                    minValue = value
                }
                if(value == minValue) {
                    minIndexes.add(i)
                }
            }
            // 2. find building point
        }

        return 0
    }
}
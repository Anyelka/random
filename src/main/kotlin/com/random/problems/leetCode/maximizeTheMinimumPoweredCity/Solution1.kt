package com.random.problems.leetCode.maximizeTheMinimumPoweredCity

class Solution1 {
    fun maxPower(stations: IntArray, r: Int, k: Int): Long {
        return maxPower2(stations, r, k)
    }

    fun maxPower1(stations: IntArray, r: Int, k: Int): Long {
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

    // 2. Greedy solution with binary search and difference array
    //      assumptions: the result (minimum of max powers) should be in the range:
    //          min: min value of stations in a city
    //          max: sum of stations + k
    //              -> (in theory) if r is big enough, all stations can power all the cities
    //                  + we can increase the power of each city k times with k new stations
    //      algorithm:
    //          1. create a diff array where each index will contain the difference in power compared to the previous city
    //          2. search between theoretical min and max of minimum max power with binary search
    //          -> check each of
    fun maxPower2(stations: IntArray, r: Int, k: Int): Long {
        val n = stations.size
        val differenceArray = LongArray(n + 1) { 0L }

        for(i in stations.indices) {
            val left = maxOf(i - r, 0)
            val right = minOf(i + r + 1, n)
            differenceArray[left] += stations[i].toLong()
            differenceArray[right] -= stations[i].toLong()
        }

        fun canAchieve(targetPower: Long): Boolean {
            var currentPower = 0L
            var currentK = k.toLong()
            val differenceArrayCopy = differenceArray.copyOf()
            for(i in stations.indices) {
                currentPower += differenceArrayCopy[i]
                if(currentPower < targetPower) {
                    val additionalStationsNeeded = targetPower - currentPower
                    if(additionalStationsNeeded > currentK) return false
                    currentK -= additionalStationsNeeded
                    currentPower += additionalStationsNeeded
                    val end = minOf(i + 2 * r + 1, n)
                    differenceArrayCopy[end] -= additionalStationsNeeded
                }
            }
            return true
        }

        var low = stations.min().toLong()
        var high = stations.sumOf { it.toLong() } + k

        var result = low
        while(low <= high) {
            val targetPower = (low + high) / 2L
            if(canAchieve(targetPower)) {
                result = targetPower
                low = targetPower + 1
            } else {
                high = targetPower - 1
            }
        }

        return result
    }
}
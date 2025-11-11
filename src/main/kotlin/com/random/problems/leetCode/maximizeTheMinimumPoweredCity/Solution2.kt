package com.random.problems.leetCode.maximizeTheMinimumPoweredCity

class Solution2 {
    fun maxPower(stations: IntArray, r: Int, k: Int): Long {
        val differenceArray = createDiffArray(stations, r)

        var left = stations.minOf { it.toLong() }
        var right = stations.sumOf { it.toLong() } + k

        var maxPower = left
        while(left <= right) {
            val currentPower = (left + right) / 2L
            if(canAchievePower(currentPower, differenceArray, r, k)) {
                maxPower = currentPower
                left = currentPower + 1
            } else {
                right = currentPower - 1
            }
        }

        return maxPower
    }

    private fun createDiffArray(stations: IntArray, r: Int): LongArray {
        val diffArray = LongArray(stations.size + 1) { 0L }
        for(i in stations.indices) {
            val firstInRange = maxOf(i - r, 0)
            val lastOutOfRange = minOf(i + r + 1, stations.size)
            diffArray[firstInRange] += stations[i].toLong()
            diffArray[lastOutOfRange] -= stations[i].toLong()
        }
        return diffArray
    }

    private fun canAchievePower(targetPower: Long, differenceArray: LongArray, r: Int, k: Int): Boolean {
        var remainingStations = k.toLong()
        var currentPower = 0L
        val diffArray = differenceArray.copyOf()
        for(i in 0 until diffArray.lastIndex) {
            currentPower += diffArray[i]
            if(targetPower > currentPower) {
                val powerDifference = targetPower - currentPower
                if(powerDifference > remainingStations) return false
                currentPower += powerDifference
                remainingStations -= powerDifference
                val endOfAddedPowerRange = minOf((i + 2 * r + 1), differenceArray.lastIndex)
                diffArray[endOfAddedPowerRange] -= powerDifference
            }
        }

        return true
    }
}
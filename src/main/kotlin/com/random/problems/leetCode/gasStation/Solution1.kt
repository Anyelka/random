package com.random.problems.leetCode.gasStation

class Solution1 {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        return canCompleteCircuit3(gas,cost)
    }

    // 1. Brute Force
    //      TC: O(n ^ 2)
    //      SC: O(1)

    fun canCompleteCircuit1(gas: IntArray, cost: IntArray): Int {
        for(i in gas.indices) {
            var currentStation = i
            var currentGas = gas[currentStation]
            while(currentGas >= cost[currentStation]) {
                currentGas -= cost[currentStation]
                currentStation = if(currentStation == gas.lastIndex) 0 else currentStation + 1
                currentGas += gas[currentStation]
                if(currentStation == i) return i
            }
        }
        return -1
    }

    /*
        gas             1   2   3   4   5       |       2   3   4       |       5   8   2   8
        cost            3   4   5   1   2       |       3   4   3       |       6   5   6   6

        difference in
        gas and cost   -2  -2  -2   3   3       |      -1  -1   1       |      -1   3  -4   2

        total:          0  -2  -4  -1   2       |      -1  -1   0       |       0   3  -1   1
            starting from 3:
                        4   2   0   3   6                                       1   4   0   2
        we need one circle where the total gas-cost difference never dips below 0
    */

    // 2. One loop at max 2 times with only allowing the total of gas and cost differences to be non-negative
    //      TC: O(n)
    //      SC: O(1)
    fun canCompleteCircuit2(gas: IntArray, cost: IntArray): Int {
        var totalGasCostDifference = 0

        var startIndex = 0

        var i = 0
        var loops = 0


        fun getNextIndex(index: Int) = if(index == gas.lastIndex) {
            loops++
            0
        } else index + 1

        while(loops < 2) {
            totalGasCostDifference += gas[i] - cost[i]

            if(totalGasCostDifference < 0) {
                i = getNextIndex(i)
                totalGasCostDifference = 0
                startIndex = i
                continue
            }

            i = getNextIndex(i)

            if(i == startIndex) return startIndex
        }

        return -1
    }

    // 3. One loop:
    //      TC: O(n)
    //      SC: O(1)
    fun canCompleteCircuit3(gas: IntArray, cost: IntArray): Int {
        var totalGas = 0
        var gasLeft = 0
        var start = 0
        for(i in gas.indices) {
            totalGas += gas[i] - cost[i]
            gasLeft += gas[i] - cost[i]
            if(gasLeft < 0) {
                start = i + 1
                gasLeft = 0
            }
        }
        return if(totalGas < 0) -1 else start
    }
}
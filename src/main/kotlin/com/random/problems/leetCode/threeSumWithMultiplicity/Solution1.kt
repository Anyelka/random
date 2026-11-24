package com.random.problems.leetCode.threeSumWithMultiplicity

class Solution1 {
    // 1. 2Sum Map approach with 2 loops
    //  - map for counting the occurences of 2 number sums
    //  - inner loop goes from 0 up to i
    fun threeSumMulti(arr: IntArray, target: Int): Int {
        val sumCount = mutableMapOf<Int,Int>()

        var result = 0
        val mod = 1000000007
        for(i in arr.indices) {
            result = (result + (sumCount[target - arr[i]] ?: 0)) % mod
            for(j in 0..<i) {
                val sum = arr[i]+arr[j]
                sumCount[sum] = (sumCount[sum] ?: 0) + 1
            }
        }
        return result
    }
}
package com.random.problems.leetCode.findXSumOfAllKLongSubarrays

import java.util.PriorityQueue

class Solution1 {
    fun findXSum(nums: IntArray, k: Int, x: Int): IntArray {
        return findXSum2(nums, k, x)
    }

    // 1. Solution:
    //      TC: O((n-k+1) * (k*log(k) + k + k)) = ~ O(n * k * log(k))
    //      SC: O(n+k)
    fun findXSum1(nums: IntArray, k: Int, x: Int): IntArray {
        val result = IntArray(nums.size - k + 1)
        val comparator = compareBy<Map.Entry<Int, Int>> { -it.value }.thenBy { -it.key }
        for (i in 0..(nums.size - k)) {
            val occurenceMap = mutableMapOf<Int, Int>()
            for (j in i..(i + k - 1)) {
                val value = nums[j]
                occurenceMap[value] = occurenceMap.getOrDefault(value, 0) + 1
            }
            result[i] = occurenceMap.entries.sortedWith(comparator).take(x).sumOf { it.value * it.key }
        }
        return result
    }

    // 2. Solution
    //      TC: O(n * (k * log(k)  + k)) = O(n * k * log(k))
    //      SC: O(n + k)
    fun findXSum2(nums: IntArray, k: Int, x: Int): IntArray {
        val comparator = compareBy<Map.Entry<Int, Int>> { -it.value }.thenBy { -it.key }

        val result = IntArray(nums.size - k + 1)
        val occurrenceMap = mutableMapOf<Int, Int>()

        for (i in 0..<nums.size) {
            val value = nums[i]
            occurrenceMap[value] = occurrenceMap.getOrDefault(value, 0) + 1
            if (i >= k - 1) {
                result[i - k + 1] = occurrenceMap.entries.sortedWith(comparator).take(x).sumOf { it.value * it.key }
                val firstValue = nums[i - k + 1]
                occurrenceMap[firstValue] = occurrenceMap.getOrDefault(firstValue, 0) - 1
                if (occurrenceMap[firstValue] == 0) occurrenceMap.remove(firstValue)
            }
        }
        return result
    }

}
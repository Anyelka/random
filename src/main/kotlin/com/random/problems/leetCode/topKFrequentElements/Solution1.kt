package com.random.problems.leetCode.topKFrequentElements

class Solution1 {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        return topKFrequent1(k, nums)
    }

    // Solution 1:
    //  one-liner with functional kotlin
    //      - group numbers by occurrence into map
    //      - sort by occurrence in decreasing order
    //      - take first k
    //
    //  TC: O(n) + O(n) + O(n) + O(m * log(m)) + O(m) + O(k) + O(k) = O(n + m * log(m))
    //  SC: O(n + m + k)
    private fun topKFrequent1(k: Int, nums: IntArray): IntArray {
        return nums.toList().groupingBy { it }.eachCount().entries
            .sortedBy { -it.value }
            .map { it.key }
            .take(k)
            .toIntArray()
    }


}


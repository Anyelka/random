package com.random.problems.leetCode.topKFrequentElements

class Solution2 {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        return topKFrequentBucketSort(nums, k)
    }

    //  TC: O(n) + O(n) + O(n * Log(n)) + O(n) + O(k)
    //  SC: O(n)
    private fun topKFrequentBuiltInFunctions(nums: IntArray, k: Int) =
        nums.toList()
            .groupingBy { it }
            .eachCount().entries
            .sortedBy { -it.value }
            .map { it.key }
            .take(k)
            .toIntArray()

    //  Bucket sort
    //  TC: O(n)
    private fun topKFrequentBucketSort(nums: IntArray, k: Int): IntArray {
        val counts = Array(nums.size + 1) { mutableListOf<Int>() }
        val frequencyList = mutableMapOf<Int, Int>()
        for(num in nums) {
            frequencyList[num] = frequencyList[num]?.plus(1) ?: 1
        }
        for(frequencies in frequencyList) {
            counts[frequencies.value].add(frequencies.key)
        }
        val result = mutableListOf<Int>()
        var i = counts.size - 1
        while(result.size < k) {
            for(num in counts[i]) {
                if(result.size < k) {
                    result.add(num)
                }
            }
            i--
        }
        return result.toIntArray()
    }
}
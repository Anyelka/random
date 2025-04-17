package com.random.problems.leetCode.`3sum`

class Solution2 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        return threeSum1(nums)
    }

    // Solution 1:
    //  Iterate through each number and reduce the problem to a 2Sum
    //
    //      TC:     O(n ^ 2)
    //      SC:     O(n ^ 2)
    //
    private fun threeSum1(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        for ((i, num) in nums.withIndex()) {
            val twoSumDifference = mutableMapOf<Int, Int>()
            for (j in i+1 until nums.size) {
                twoSumDifference[nums[j]]?.let { result.add(listOf(num, nums[j], it).sorted()) }
                twoSumDifference[0 - num - nums[j]] = nums[j]
            }
        }
        return result.toList()
    }

    // Solution2:
    //  Reduce to sorted 2Sum
    //      TC:        O(n * log(n)) + O(n ^ 2) = O(n ^ 2)
    //      SC:        O(1)
    private fun threeSum2(nums: IntArray): List<List<Int>> {
        val sortedNums = nums.sorted()
        return sortedThreeSum(sortedNums)
    }

    private fun sortedThreeSum(nums: List<Int>): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        for(i in nums.indices) {
            var left = i + 1
            var right = nums.size - 1
            while(left < right) {
                if(nums[i] + nums[left] + nums[right] == 0) {
                    result.add(listOf(nums[i], nums[left], nums[right]))
                    left++
                    while(nums[left] == nums[left - 1] && left < right) left++
                } else if(nums[i] + nums[left] + nums[right] < 0) {
                    left++
                } else {
                    right--
                }
            }
        }
        return result.toList()
    }
}
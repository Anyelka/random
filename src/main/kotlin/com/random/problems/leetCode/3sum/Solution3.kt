package com.random.problems.leetCode.`3sum`

class Solution3 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        return threeSum2(nums)
    }

    fun threeSum1(nums: IntArray): List<List<Int>> {
        val target = 0

        val result = mutableSetOf<List<Int>>()


        // TC: O(n)
        for(i in nums.indices) {
            val twoSumTarget = target - nums[i]
            val twoSumResults = twoSum(nums, twoSumTarget, i)
            result.addAll(
                twoSumResults.map { (it + nums[i]).sorted() }
            )
        }
        return result.toList()
    }

    // TC: O (n^2)
    fun twoSum(nums: IntArray, target: Int, i: Int): Set<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        val differenceMap = mutableMapOf<Int,Int>()
        for(j in (i+1)..nums.lastIndex) {
            differenceMap[nums[j]]?.let {
                result.add(listOf(nums[j], it).sorted())
            }
            differenceMap[target - nums[j]] = nums[j]
        }
        return result
    }

    // 2. Two pointers with sorted nums
    fun threeSum2(nums: IntArray): List<List<Int>> {
        val target = 0
        val result = mutableSetOf<List<Int>>()

        val sortedNums = nums.sorted()
        // TC: O(n)
        for(i in sortedNums.indices) {
            val twoSumTarget = target - sortedNums[i]
            val twoSumResults = twoSum2(sortedNums, twoSumTarget, i)
            result.addAll(
                twoSumResults.map { (it + sortedNums[i]).sorted() }
            )
        }
        return result.toList()
    }

    fun twoSum2(nums: List<Int>, target: Int, i: Int): Set<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        var left = i+1
        var right = nums.lastIndex
        while(left < right) {
            if(nums[left] + nums[right] == target) {
                result.add(listOf(nums[left], nums[right]))
                left++
            } else if(nums[left] + nums[right] > target) {
                right--
            } else {
                left++
            }
        }
        return result
    }
}
package com.random.problems.leetCode.`4sum`

class Solution2 {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val sortedNums = nums.sorted()
        return nSumSorted(sortedNums, target.toLong(), 4, -1)
    }

    fun fourSumSorted(nums: List<Int>, target: Int): List<List<Int>> {
        val results = mutableSetOf<List<Int>>()
        // TC: O(n^2) * O(twoSum)
        for(i in 0..nums.lastIndex) {
            for(j in i+1..nums.lastIndex) {
                val nextTarget = target.toLong() - nums[i] - nums[j]
                val twoSumResults = twoSumImproved(nums, nextTarget, j)
                results.addAll(twoSumResults.map { listOf(nums[i], nums[j]) + it })

            }
        }
        return results.toList()
    }

    // TC: O(n^2)
    fun twoSum(nums: List<Int>, target: Long, j: Int): Set<List<Int>> {
        val results = mutableSetOf<List<Int>>()
        for(k in j+1..nums.lastIndex) {
            for(l in k+1..nums.lastIndex) {
                if(nums[k].toLong() + nums[l] == target) {
                    results.add(listOf(nums[k], nums[l]))
                }
            }
        }
        return results
    }

    // TC: O(n)
    fun twoSumImproved(nums: List<Int>, target: Long, j: Int): Set<List<Int>> {
        val results = mutableSetOf<List<Int>>()
        var left = j + 1
        var right = nums.lastIndex
        while(left < right) {
            if(nums[left].toLong() + nums[right] == target) {
                results.add(listOf(nums[left], nums[right]))
                left++
            } else if(nums[left].toLong() + nums[right] > target) {
                right--
            } else {
                left++
            }
        }
        return results
    }

    // General N-SUM
    fun nSumSorted(nums: List<Int>, target: Long, n: Int, prevIndex: Int): List<List<Int>> {
        if(n == 2) return twoSumImproved(nums, target, prevIndex).toList()

        val results = mutableSetOf<List<Int>>()
        for(i in (prevIndex + 1)..nums.lastIndex) {
            val current = nums[i]
            val nextTarget = target - current
            val nextSubsets = nSumSorted(nums, nextTarget, n-1, i)
            results.addAll(
                nextSubsets.map { listOf(current) + it}
            )
        }
        return results.toList()
    }
}
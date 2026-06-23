package com.random.problems.leetCode.`4sum`

class Solution1 {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        return fourSum2(nums, target)
    }

    // 1. Backtracking
    //      decision tree with take/leave for each number
    //      +
    //      memo
    //      - without memo: very high complexity: O(2^n)
    //      - with memo: difficult to select a proper key for caching
    //
    fun fourSum1(nums: IntArray, target: Int): List<List<Int>> {
        val memo = mutableMapOf<String, Set<List<Int>>>()

        fun fourSum1(collected: List<Int>, i: Int, left: Int, target: Int): Set<List<Int>> {
            val cacheKey = "$i|$left|$target|${collected.sorted()}"
            if (memo.containsKey(cacheKey)) return memo[cacheKey]!!

            if (left == 0 && target == 0) {
                memo[cacheKey] = setOf(collected.sorted())
                return memo[cacheKey]!!
            }
            if (i >= nums.size || left < 0) {
                memo[cacheKey] = emptySet()
                return memo[cacheKey]!!
            }

            val current = nums[i]
            val take = fourSum1(collected + listOf(current), i + 1, left - 1, target - current)
            val leave = fourSum1(collected, i + 1, left, target)

            memo[cacheKey] = take + leave
            return memo[cacheKey]!!
        }

        return fourSum1(emptyList(), 0, 4, target).toList()
    }

    // 2. Reduced to 2Sum
    // Time complexity: O(n^2) * O(twoSum)
    fun fourSum2(nums: IntArray, target: Int): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        for(i in nums.indices) {
            for(j in i+1..nums.lastIndex) {
                val nextTarget = target.toLong() - nums[i] - nums[j]

                val twoSumResult = twoSum2(nums, j + 1, nextTarget)
                twoSumResult.forEach {
                    result.add((it + listOf(nums[i], nums[j])).sorted())
                }
            }
        }

        return result.toList()
    }

    // 1. Two Sum: Naive two loops
    //      TC: O(n^2)
    fun twoSum1(nums: IntArray, start: Int, target: Long): Set<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        for(i in start..nums.lastIndex) {
            for(j in i+1..nums.lastIndex) {
                if(nums[i].toLong() + nums[j] == target) {
                    result.add(listOf(nums[i], nums[j]).sorted())
                }
            }
        }
        return result
    }

    // 2. Two Sum:
    //      TC: O(n)
    fun twoSum2(nums: IntArray, start: Int, target: Long): Set<List<Int>> {
        val differenceMap = mutableMapOf<Long, Long>()
        val result = mutableSetOf<List<Int>>()
        for(i in start..nums.lastIndex) {
            val current = nums[i].toLong()
            val diff = target - current
            if(differenceMap.containsKey(current)) {
                result.add(listOf(current.toInt(), diff.toInt()).sorted())
            }
            differenceMap[diff] = current
        }
        return result
    }

}
package com.random.problems.leetCode.continuousSubarraySum

class Solution1 {
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        return checkSubarraySum3(nums, k)
    }

    // 1. Naive 3 loops
    //  TC: O(n^3)
    fun checkSubarraySum1(nums: IntArray, k: Int): Boolean {
        for(i in nums.indices) {
            for(j in i+1..nums.lastIndex) {
                var sum = 0
                for(l in i..j) {
                    sum+= nums[l]
                }
                if(sum % k == 0) return true
            }
        }
        return false
    }

    // 2. Prefix sum with prefix array and saving the prefix sum mod to a map
    //      to look up indexes quickly
    fun checkSubarraySum2(nums: IntArray, k: Int): Boolean {
        val prefixSum = IntArray(nums.size + 1) { 0 }
        var prefix = 0
        for(i in nums.indices) {
            prefixSum[i + 1] = (prefix + nums[i]) % k
            prefix = prefixSum[i + 1]
        }

        val modsWithIndex = mutableMapOf<Int,Int>()
        modsWithIndex[0] = -1

        for(i in nums.indices) {
            if(modsWithIndex.containsKey(prefixSum[i + 1])) {
                if(i > 1 + modsWithIndex[prefixSum[i + 1]]!!) return true
            } else {
                modsWithIndex[prefixSum[i + 1]] = i
            }
        }

        return false
    }

    // 3. prefix sum + k mod prefix map
    fun checkSubarraySum3(nums: IntArray, k: Int): Boolean {
        var prefix = 0
        val modsWithIndex = mutableMapOf<Int,Int>()
        modsWithIndex[0] = -1

        for(i in nums.indices) {
            prefix = (prefix + nums[i]) % k
            if(modsWithIndex.containsKey(prefix)) {
                if(i > 1 + modsWithIndex[prefix]!!) return true
            } else {
                modsWithIndex[prefix] = i
            }
        }

        return false
    }
}
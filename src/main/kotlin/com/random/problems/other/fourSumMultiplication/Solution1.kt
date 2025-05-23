package com.random.problems.other.fourSumMultiplication

class Solution1: Solution() {
    // Brute force solution:
    //  TC: O(n ^ 4)
    //  SC: O(n ^ 4)
    override fun findQuadruplets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        for(i in 0..nums.lastIndex) {
            for(j in i+1..nums.lastIndex) {
                for(k in j+1..nums.lastIndex) {
                    for(l in k+1..nums.lastIndex) {
                        if(nums[i] * nums[j] * nums[k] == nums[l]) result.add(listOf(i,j,k,l))
                    }
                }
            }
        }
        return result
    }

}
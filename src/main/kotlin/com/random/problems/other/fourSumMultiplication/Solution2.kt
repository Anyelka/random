package com.random.problems.other.fourSumMultiplication

class Solution2: Solution() {
    // 2. :
    //  TC: O(n ^ 3)
    //  SC: O(n ^ 3)
    override fun findQuadruplets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val productMap = mutableMapOf<Int, MutableList<Triple<Int,Int,Int>>>()
        for (i in 0..nums.lastIndex) {
            for (j in i + 1..nums.lastIndex) {
                for (k in j + 1..nums.lastIndex) {
                    val product = nums[i] * nums[j] * nums[k]
                    productMap.computeIfAbsent(product) { mutableListOf() }.add(Triple(i, j, k))
                }
            }
        }
        for(l in 0..nums.lastIndex) {
            productMap[nums[l]]?.forEach { (i, j, k) ->
                if(l > k) result.add(listOf(i,j,k,l))
            }
        }
        return result
    }

}
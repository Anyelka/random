package com.random.problems.leetCode.singleNumber

class Solution1 {
    fun singleNumber(nums: IntArray): Int {
        return singleNumber1(nums)
    }

    //      TC:     O(2n) = O(n)
    //      SC:     O(n/2)= O(n)
    fun singleNumber1(nums: IntArray): Int {
        val occurences = nums.toList().groupingBy { it }.eachCount()
        return occurences.entries.find { it.value == 1 }!!.key
    }


    //  4 XOR 4
    //  100
    //  ^
    //  100
    // =000
    fun singleNumber2(nums: IntArray): Int {
        var result = 0
        for(num in nums) {
            result = result.xor(num)
        }
        return result
    }
}
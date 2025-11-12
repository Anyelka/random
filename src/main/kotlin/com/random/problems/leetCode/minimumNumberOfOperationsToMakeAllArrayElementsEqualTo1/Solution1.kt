package com.random.problems.leetCode.minimumNumberOfOperationsToMakeAllArrayElementsEqualTo1

class Solution1 {

    fun minOperations(nums: IntArray): Int {
        var ones = 0
        for(i in nums.indices) {
            if(nums[i] == 1) ones++
        }
        if(ones > 0) return nums.size - ones

        var minSubarrayLengthWith1Gcd = Int.MAX_VALUE

        for(i in nums.indices) {
            var currentGcd = 0
            for(j in i..nums.lastIndex) {
                currentGcd = gcd(currentGcd, nums[j])
                if(currentGcd == 1) {
                    minSubarrayLengthWith1Gcd = minOf(minSubarrayLengthWith1Gcd, j - i + 1)
                    break
                }
            }
        }

        return if(minSubarrayLengthWith1Gcd == Int.MAX_VALUE) -1 else (minSubarrayLengthWith1Gcd - 1) + (nums.size - 1)
    }


    fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }
}
package com.random.problems.leetCode.maximumSubarrayWithEqualProducts

class Solution1 {
    fun maxLength(nums: IntArray): Int {
        return maxLength1(nums)
    }

    // 1. Brute force with GCD and LCM
    // TC: O(n^2)
    fun maxLength1(nums: IntArray): Int {
        var maxLength = 1
        for(i in nums.indices) {
            var product = 1L
            var gcd = 1L
            var lcm = 1L
            for(j in i..nums.lastIndex) {
                product *= nums[j]
                gcd = gcd(gcd, nums[j].toLong())
                lcm = lcm(lcm, nums[j].toLong())
                if(product == gcd * lcm) {
                    maxLength = maxOf(maxLength, (j-i) + 1)
                }
            }
        }
        return maxLength
    }

    fun gcd(a: Long, b: Long): Long {
        return if(b == 0L) a else gcd(b, a % b)
    }

    fun lcm(a: Long, b: Long): Long {
        return (a / gcd(a,b)) * b
    }
}
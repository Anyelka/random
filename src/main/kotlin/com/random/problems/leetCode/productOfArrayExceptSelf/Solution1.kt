package com.random.problems.leetCode.productOfArrayExceptSelf

class Solution1 {
    //  7   2   4   1   5
    // 40  140 70  280  56  totalProduct    =   280
    fun productExceptSelf(nums: IntArray): IntArray {
        return productExceptSelf2(nums)
    }

    //  WRONG SOLUTION: it uses division
    fun productExceptSelf1(nums: IntArray): IntArray {
        val result = IntArray(nums.size) { 0 }
        var totalProductWithoutZeroes = 1
        var zeroCount = 0
        for(i in 0..nums.lastIndex) {
            if(nums[i] != 0) {
                totalProductWithoutZeroes *= nums[i]
            } else  {
                zeroCount++
            }
        }
        if(zeroCount > 1) return result
        for(i in 0..nums.lastIndex) {
            if(zeroCount > 0) {
                if(nums[i] == 0) result[i] = totalProductWithoutZeroes
                else result[i] = 0
            } else {
                result[i] = totalProductWithoutZeroes / nums[i]
            }
        }
        return result
    }

    fun productExceptSelf2(nums: IntArray): IntArray {
        val result = IntArray(nums.size) { 1 }

        var leftProduct = 1
        var rightProduct = 1
        for(i in nums.indices) {
            result[i] *= leftProduct
            result[nums.lastIndex - i] *= rightProduct
            leftProduct *= nums[i]
            rightProduct *= nums[nums.lastIndex - i]
        }

        return result
    }

}

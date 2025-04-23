package com.random.problems.leetCode.nextPermutation

class Solution2 {
    fun nextPermutation(nums: IntArray): Unit {
        var i = nums.size - 2
        while(i >= 0 && nums[i] >= nums[i + 1]) i--

        if(i < 0) {
            nums.reverseFrom(0)
            return
        }

        var j = nums.size - 1
        while(nums[j] <= nums[i]) j--

        nums.swap(i, j)

        nums.reverseFrom(i + 1)
    }

    fun IntArray.swap(index1: Int, index2: Int) {
        val temp = this[index1]
        this[index1] = this[index2]
        this[index2] = temp
    }

    fun IntArray.reverseFrom(index: Int) {
        // reverse the array from index, until the end
        var l = index
        var r = this.size - 1
        //  5,  3,  2,  4,  3,  2,  1
        //  NP:
        //  5,  3,  3,  1,  2,  2,  4
        // step by step:

        // 1. swap i and j:
        //  5,  3,  3,  4,  2,  2,  1,  1
        while(l < r) {
            this.swap(l,r)
            l++
            r--
        }
    }
}
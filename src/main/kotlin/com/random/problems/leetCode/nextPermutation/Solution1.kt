package com.random.problems.leetCode.nextPermutation

class Solution1 {

    // 1. Next permutation solution:
    //      Find the element with the smallest place value (pivot element), where all elements to the right
    //          (with lower place values) are in a strictly decreasing order
    //      Find the smallest element from the right subarray (swapping element) that is higher than the pivot element
    //      We can change the pivot element to this swapping element and rearrange the right subarray to
    //          be in strictly increasing order
    //      Note: If we swap the pivot element with the swapping element, the right subarray will stay in strictly decreasing order
    //
    //
    //      Time complexity:    O(n)    =   O(n) + O(n) + O(n)
    //      Space complexity:    O(1)
    //
    fun nextPermutation(nums: IntArray): Unit {
        // find the first place that can be the pivot:
        //      the whole can be increased by swapping the pivot with a number to the right of the pivot
        var i = nums.size - 2
        while (i >= 0 && nums[i] >= nums[i + 1]) i--
        //  if the number is at its highest permutation, it cant be rearranged
        //      we need the lowest permutation = reverse of the highest permutation
        if (i < 0) {
            nums.reverse()
            return
        }
        // find the first place that can be swapped with the pivot:
        //      the first number at a higher place that is greater than the pivot
        //      if the pivot exists, this one should exist too
        var j = nums.size - 1
        while (j > 0 && nums[i] >= nums[j]) j--

        rearrange(nums, i, j)
        return
    }

    private fun rearrange(nums: IntArray, i: Int, j:Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
        val initial = nums.copyOf()
        for(index in i + 1..<nums.size) {
            nums[index] = initial[nums.size - (index - i)]
        }
    }

}

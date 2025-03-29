package com.random.problems.leetCode.binarySearch

class Solution2 {
    fun search(nums: IntArray, target: Int): Int {
        return binarySearchIt(nums, target)
    }
    fun binarySearchRec(nums: IntArray, target: Int): Int {
        return binarySearchRec(nums, target, 0, nums.size - 1)
    }

    fun binarySearchRec(nums: IntArray, target: Int, start: Int, end: Int): Int {
        if(start > end) return -1
        val mid = (end + start) / 2
        return if(nums[mid] == target) {
            return mid
        } else if(nums[mid] > target) {
            return binarySearchRec(nums, target, start, mid - 1)
        } else {
            return binarySearchRec(nums, target, mid + 1, end)
        }
    }

    fun binarySearchIt(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.size - 1
        while(start <= end) {
            val mid = (start + end) / 2
            when {
                nums[mid] == target -> return mid
                nums[mid] < target -> start = mid + 1
                else -> end = mid - 1
            }
        }
        return -1
    }
}
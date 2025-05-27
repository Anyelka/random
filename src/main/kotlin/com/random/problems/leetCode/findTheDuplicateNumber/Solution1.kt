package com.random.problems.leetCode.findTheDuplicateNumber

class Solution1 {
    // 1. Floyd's Cycle Finding algorithm
    fun findDuplicate(nums: IntArray): Int {
        var slow = nums[0]
        var fast = nums[0]
        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while(fast != slow)

        var slow2 = nums[0]
        while (slow != slow2) {
            slow = nums[slow]
            slow2 = nums[slow2]
        }
        return slow
    }
}
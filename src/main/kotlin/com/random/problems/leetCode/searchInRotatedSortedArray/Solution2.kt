package com.random.problems.leetCode.searchInRotatedSortedArray

class Solution2 {
    fun search(nums: IntArray, target: Int): Int {
        fun getK(): Int {
            var k = nums.lastIndex
            while(k > 0 && nums[k-1] < nums[k]) k--
            return nums.size - k
        }

        val k = getK()

        var l = 0
        var r = nums.lastIndex

        while(l <= r) {
            val mid = (l + r) / 2
            val index = if(mid >= k) mid - k else (nums.lastIndex + mid + 1 - k)
            if(nums[index] == target) return index
            else if(nums[index] < target) l = mid + 1
            else r = mid - 1
        }
        return -1
    }
}
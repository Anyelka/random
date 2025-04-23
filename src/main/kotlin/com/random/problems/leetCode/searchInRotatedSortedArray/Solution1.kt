package com.random.problems.leetCode.searchInRotatedSortedArray

class Solution1 {
    //  Solution 1:
    //      binary search with shifting elements by k to the left
    //      TC: O(log(n)) + O(n) = O(n)
    //      SC: O(1)
    fun search(nums: IntArray, target: Int): Int {
        // O(log(n)) = binary search
        //  binary search in an ascending array:

        //  this could be done with BS too
        var k = nums.size - 1
        while(k > 0 && nums[k] > nums[k - 1]) k--
        k = nums.size - k

        //  0   1   2   3   4   5   6   7
        //          k = 3
        //  3   4   5   6   7   0   1   2

        fun search(start: Int, end: Int): Int {
            var left = start
            var right = end
            while(left <= right) {
                val mid = (left + right) / 2
                val index = if(mid >= k) (mid - k) else (nums.size - k + mid)
                if(nums[index] == target)
                    return index
                else if(nums[index] < target)
                    left = mid + 1
                else
                    right = mid - 1
            }
            return -1
        }

        return search(0, nums.size - 1)
    }
}
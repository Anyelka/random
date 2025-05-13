package com.random.problems.leetCode.slidingWindowMaximum

import java.util.PriorityQueue
import kotlin.math.max

class Solution1 {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        return maxSlidingWindow3(nums, k)
    }

    //  1. Solution:
    //      slide the window and always find the max in each window
    //
    //      TC: O(n^2)
    //      SC: O(n)
    private fun maxSlidingWindow1(nums: IntArray, k: Int): IntArray {
        var i = 0
        var j = 1
        val maxes = mutableListOf<Int>()

        while (j <= nums.size) {
            if (j - i + 1 <= k) {
                // grow interval
                j++
            } else {
                // slide window
                maxes.add(nums.copyOfRange(i, j).max())
                i++
                j++
            }
        }

        return maxes.toIntArray()
    }

    //  2. Solution:
    //  - using max heap
    //      TC: O(n * log(k)) + n * k) = O(n*k)
    ///     SC: O(n + k)
    private fun maxSlidingWindow2(nums: IntArray, k: Int): IntArray {
        var i = 0
        var j = 1
        val maxes = mutableListOf<Int>()

        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        maxHeap.add(nums[0])

        while (j <= nums.size) {
            if (j - i + 1 <= k) {
                maxHeap.add(nums[j])
                j++
            } else {
                maxes.add(maxHeap.first())
                if (j < nums.size) {
                    maxHeap.add(nums[j])
                    maxHeap.remove(nums[i])
                }
                i++
                j++
            }
        }

        return maxes.toIntArray()
    }


    // 3. Solution:
    //
    //  TC: O(n * log(k))
    //  SC: O(n + k)
    private fun maxSlidingWindow3(nums: IntArray, k: Int): IntArray {
        val maxes = mutableListOf<Int>()
        val maxHeap = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.first })

        for (i in nums.indices) {
            maxHeap.add(nums[i] to i)
            while (maxHeap.peek().second <= i - k) maxHeap.poll()
            if (i >= k - 1) maxes.add(maxHeap.peek().first)
        }

        return maxes.toIntArray()
    }

}
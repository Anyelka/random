package com.random.problems.leetCode.jumpGame

class Solution1 {
    fun canJump(nums: IntArray): Boolean {
        return canJump3(nums)
    }

    // Solution 1: Naive recursion
    //
    //      TC:     O(m ^ n)
    //      SC:     O(n)
    private fun canJump1(nums: IntArray): Boolean {
        return canJumpFrom(nums, 0)
    }

    fun canJumpFrom(nums: IntArray, position: Int): Boolean {
        if(position == nums.size - 1) return true
        return (1..nums[position]).any { canJumpFrom(nums, position + it) }
    }

    // 2. Memo: Overflow
    //
    //      TC:     O(n ^ 2)
    //      SC:     O(n)
    private fun canJump2(nums: IntArray): Boolean {
        return canJumpFromMemo(nums, 0, mutableMapOf())
    }
    fun canJumpFromMemo(nums: IntArray, position: Int, memo: MutableMap<Int, Boolean>): Boolean {
        if(position == nums.size - 1) return true
        if(memo.containsKey(position)) return memo[position]!!
        val canJumpToEnd = (1..nums[position]).any {
            canJumpFromMemo(nums, position + it, memo)
        }
        memo[position] = canJumpToEnd
        return canJumpToEnd
    }

    // 3. Tab
    //
    //      TC:     O(n * m)
    //      SC:     O(n)
    private fun canJump3(nums: IntArray): Boolean {
        val dp = Array(nums.size) { false }
        dp[0] = true
        for(i in nums.indices) {
            val maxStep = nums[i]
            if(dp[i]) {
                for(j in 1..maxStep) {
                    if(i + j < nums.size) dp[i+j] = true
                }
            }
        }
        return dp[nums.size - 1]
    }

    // 4. Queue: too slow because it tries to explore all options
    private fun canJump4(nums: IntArray): Boolean {
        val queue = ArrayDeque<Int>().also { it.addFirst(0) }
        val visited = mutableListOf<Int>()
        while(queue.isNotEmpty()) {
            val position = queue.removeFirst()
            visited.add(position)
            if(nums[position] == 0) continue
            for(j in 1..nums[position]) {
                val nextPosition = position + j
                if(nextPosition == nums.size - 1) return true
                if(!visited.contains(nextPosition)) queue.add(nextPosition)
            }
        }
        return false
    }
}
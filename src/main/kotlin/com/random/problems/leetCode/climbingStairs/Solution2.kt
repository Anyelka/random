package com.random.problems.leetCode.climbingStairs

class Solution2 {
    fun climbStairs(n: Int): Int {
        // start from step = 0
        // get to step = n
        // how many ways ?
        // subproblem = how many ways can w reach step = x ?
        //      we can get to step = x by:
        //          - get to step = x - 1 and step up 1
        //          - get to step = x - 2 and step up 2
        //      so the number of ways we can get to step = x is:
        //         ways of getting to step = x - 1 + ways of getting to step = x - 2

        //      base cases:
        //      to get to 0: 1 ways (we are standing at it right at the start)
        //      to get to 1: 1 ways: step 1 up from 0

        //      Time complexity:        O(n)
        //      Space complexity:       O(n)

        val memo = mutableMapOf(0 to 1, 1 to 1)
        fun climb(n: Int): Int = memo.getOrPut(n) { climb(n - 1 ) + climb(n - 2) }
        return climb(n)
    }

}
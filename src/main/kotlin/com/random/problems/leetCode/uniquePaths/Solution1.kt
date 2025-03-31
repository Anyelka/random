package com.random.problems.leetCode.uniquePaths

class Solution1 {
    private val possibleSteps= listOf((1 to 0), (0 to 1))

    fun uniquePaths(m: Int, n: Int): Int {
        return uniquePathsRecursiveMemo(1 to 1, m to n, mutableMapOf())
    }

    // 1. Recursive Solution:
    //
    //      Time complexity:    O(2^(n*m))
    //      Space complexity:   O(1)
    //
    private fun uniquePathsRecursive(start: Pair<Int,Int>, target: Pair<Int,Int>): Int {
        if(start.first == target.first || start.second == target.second) return 1

        var waysToGetToTarget = 0
        for(step in possibleSteps) {
            val next = start + step
            waysToGetToTarget += uniquePathsRecursive(next, target)
        }

        return waysToGetToTarget
    }


    // 2. Memoized Solution:
    //
    //      Time complexity:    O(n*m)
    //      Space complexity:   O(n*m)
    //
    private fun uniquePathsRecursiveMemo(start: Pair<Int,Int>, target: Pair<Int,Int>, memo: MutableMap<Pair<Int,Int>, Int>): Int {
        if(start.first == target.first || start.second == target.second) return 1

        if(memo.containsKey(start)) return memo[start]!!

        var waysToGetToTarget = 0
        for(step in possibleSteps) {
            val next = start + step
            waysToGetToTarget += uniquePathsRecursiveMemo(next, target, memo)
        }

        memo[start] = waysToGetToTarget

        return waysToGetToTarget
    }

    operator fun Pair<Int,Int>.plus(other: Pair<Int,Int>): Pair<Int, Int> {
        return (first + other.first) to (second + other.second)
    }

    operator fun Pair<Int,Int>.minus(other: Pair<Int,Int>): Pair<Int, Int> {
        return (first - other.first) to (second - other.second)
    }
}
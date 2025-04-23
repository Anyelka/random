package com.random.problems.hackerrank.snakesAndLadders

class Solution3 {

    //  TC: O(100) = O(1)
    //  SC: O(100) = O(1)
    fun quickestWayUp(ladders: Array<Array<Int>>, snakes: Array<Array<Int>>): Int {
        val laddersMap = ladders.associate { it[0] to it[1] }
        val snakesMap = snakes.associate { it[0] to it[1] }

        val dp = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Pair<Int, Int>>().also { it.add(1 to 0) }

        fun getNext(square: Int) =
            when {
                laddersMap.containsKey(square) -> laddersMap[square]!!
                snakesMap.containsKey(square) -> snakesMap[square]!!
                else -> square
            }

        while (queue.isNotEmpty()) {
            val (currentSquare, minStepsTo) = queue.removeFirst()
            dp[currentSquare] = minOf(dp[currentSquare] ?: Int.MAX_VALUE, minStepsTo)
            (1..6)
                .map { currentSquare + it }
                .filter { it <= 100 }
                .map { getNext(it) }
                .forEach { it
                    .takeUnless { dp.containsKey(it) }
                    ?.let { sq -> queue.add(sq to dp[currentSquare]!! + 1) }
                }
        }

        return dp[100] ?: -1
    }
}
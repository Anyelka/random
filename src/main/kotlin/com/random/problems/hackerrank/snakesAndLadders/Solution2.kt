package com.random.problems.hackerrank.snakesAndLadders

class Solution2 {
    fun quickestWayUp(ladders: Array<Array<Int>>, snakes: Array<Array<Int>>): Int {
        val laddersMap = ladders.associate { it[0] to it[1] }
        val snakesMap = snakes.associate { it[0] to it[1] }

        val minStepsTo = mutableMapOf<Int, Int>().also { it[1] = 0 }

        val squareQueue = ArrayDeque<Int>().also { it.add(1) }

        while (squareQueue.isNotEmpty()) {
            val currentSquare = squareQueue.removeFirst()

            for (i in 1..6) {
                var nextSquare = currentSquare + i
                when {
                    nextSquare > 100 -> continue
                    laddersMap.containsKey(nextSquare) -> nextSquare = laddersMap[nextSquare]!!
                    snakesMap.containsKey(nextSquare) -> nextSquare = snakesMap[nextSquare]!!
                }

                if (!minStepsTo.containsKey(nextSquare)) squareQueue.add(nextSquare)

                minStepsTo[nextSquare] = listOfNotNull(minStepsTo[nextSquare], minStepsTo[currentSquare]?.plus(1)).min()
            }
        }

        return minStepsTo[100] ?: -1
    }
}
package com.random.problems.hackerrank.snakesAndLadders

class Solution1 {

    fun quickestWayUp(ladders: Array<Array<Int>>, snakes: Array<Array<Int>>): Int {
        return quickestWayUp(ladders.toMap(), snakes.toMap())
    }

    fun Array<Array<Int>>.toMap() = this.associate { it[0] to it[1] }

    // Breadth First Search solution
//      start from square 1 with 0 required steps and go through the elements of the queue
//          we take the element from queue and store each square in a map with the value of the required minimum steps
//          go through each possible dice roll and calculate the next square
//              - add the dice roll value to the current square
//              - take the snakes and ladders into account - slide up / down
//              - do not go over 100
//          calculate the required steps for the next square by adding 1 to the required steps of the current square
//          load the next square with the required steps into the queue
//      after all elements are processed we can look up from the map how many steps are under key 100
//
//      Time complexity: O(n * m)       = O(100 * 6)
//      Space complexity: O(n)          = O(100)
//
    fun quickestWayUp(ladders: Map<Int, Int>, snakes: Map<Int, Int>): Int {
        val dp = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Pair<Int, Int>>().also { it.add(1 to 0) }
        val visited = mutableSetOf<Int>()

        while (!queue.isEmpty()) {
            val (currentSquare, stepsToCurrent) = queue.removeFirst()

            dp[currentSquare] = minOf(dp[currentSquare] ?: Int.MAX_VALUE, stepsToCurrent)

            if(!visited.add(currentSquare)) continue

            (1..6)
                .map { currentSquare + it }
                .filter { it <= 100 }
                .map {
                    when {
                        ladders.containsKey(it) -> ladders[it]!! to stepsToCurrent + 1
                        snakes.containsKey(it) -> snakes[it]!! to stepsToCurrent + 1
                        else -> it to stepsToCurrent + 1
                    }
                }
                .forEach { queue.add(it) }
        }

        return dp[100] ?: -1
    }
}
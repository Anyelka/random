package com.random.problems.hackerrank.queensAttack

class Solution1 {
    // Solution with going from queen position until we hit an obstacle in all 8 directions
    //  nice recursion, but too slow
    fun queensAttack(n: Int, k: Int, r_q: Int, c_q: Int, obstacles: Array<Array<Int>>): Int {
        var attackableSquares: Int = 0
        for (i in -1..1) {
            for (j in -1..1) {
                attackableSquares += attackableSquares(Pair(i,j),n,Pair(r_q,c_q),obstacles)
            }
        }
        return attackableSquares
    }

    fun attackableSquares(direction: Pair<Int, Int>, n: Int, queenPosition: Pair<Int, Int>, obstacles: Array<Array<Int>>): Int {
        if((direction.first == 0 && direction.second == 0)) {
            return 0
        }
        val nextPosition = queenPosition.move(direction)
        if(nextPosition.first > n || nextPosition.first < 1 || nextPosition.second > n || nextPosition.second < 1) {
            return 0
        }
        val isObstacle = obstacles.any { it[0] == nextPosition.first && it[1] == nextPosition.second }
        if(isObstacle) {
            println("Obstacle in $direction direction: (${nextPosition.first}; ${nextPosition.second})")
            return 0
        }
        return 1 + attackableSquares(direction, n, nextPosition, obstacles)
    }

    fun Pair<Int,Int>.move(moveCoordinates: Pair<Int, Int>): Pair<Int, Int> {
        return Pair(this.first + moveCoordinates.first, this.second + moveCoordinates.second)
    }
}
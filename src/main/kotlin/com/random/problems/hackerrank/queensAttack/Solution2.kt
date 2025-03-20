package com.random.problems.hackerrank.queensAttack

class Solution2 {
    fun queensAttack(n: Int, k: Int, r_q: Int, c_q: Int, obstacles: Array<Array<Int>>): Int {
        val topRightR = if(r_q > c_q) n else n+r_q-c_q
        val topRightC = if(c_q > r_q) n else n+c_q-r_q
        val bottomRightR = if(r_q+c_q < n+1) 1 else r_q+c_q-n
        val bottomRightC = if(r_q+c_q > n) n else r_q+c_q-1
        val bottomLeftR = if(c_q > r_q) 1 else r_q-c_q+1
        val bottomLeftC = if(r_q > c_q) 1 else c_q-r_q+1
        val topLeftR = if(r_q+c_q > n) n else r_q+c_q-1
        val topLeftC = if(r_q+c_q < n+1) 1 else (r_q+c_q)-n

        val lastFreeSpaces: Array<Array<Int>> = arrayOf(
            arrayOf(topRightR,topRightC),
            arrayOf(r_q, n),
            arrayOf(bottomRightR, bottomRightC),
            arrayOf(1, c_q),
            arrayOf(bottomLeftR, bottomLeftC),
            arrayOf(r_q, 1),
            arrayOf(topLeftR, topLeftC),
            arrayOf(n, c_q)
        )

        for(obstacle in obstacles) {
            val obstacleR = obstacle[0]
            val obstacleC = obstacle[1]
            if(obstacleR - r_q == obstacleC - c_q) {
                val after = lastFreeSpaces[0]
                val before = lastFreeSpaces[4]
                if((obstacleR > r_q && obstacleC > c_q) && (obstacleR < after[0] && obstacleC < after[1])) {
                    lastFreeSpaces[0] = arrayOf(obstacleR-1,obstacleC-1)
                }
                if(obstacleR < r_q && obstacleR > before[0] && obstacleC < c_q && obstacleC > before[1]) {
                    lastFreeSpaces[4] = arrayOf(obstacleR+1, obstacleC+1)
                }
            }
            if(obstacleR == r_q) {
                val after = lastFreeSpaces[1]
                val before = lastFreeSpaces[5]
                if(obstacleC > c_q && obstacleC < after[1]) {
                    lastFreeSpaces[1] = arrayOf(obstacleR, obstacleC-1)
                }
                if(obstacleC < c_q && obstacleC > before[1]) {
                    lastFreeSpaces[5] = arrayOf(obstacleR, obstacleC+1)
                }
            }
            if(obstacleR - r_q == c_q - obstacleC) {
                val after = lastFreeSpaces[2]
                val before = lastFreeSpaces[6]
                if((obstacleR < r_q && obstacleC > c_q) && (obstacleR > after[0] && obstacleC < after[1])) {
                    lastFreeSpaces[2] = arrayOf(obstacleR+1, obstacleC-1)
                }
                if(obstacleR > r_q && obstacleR < before[0] && obstacleC < c_q && obstacleC > before[1]) {
                    lastFreeSpaces[6] = arrayOf(obstacleR-1, obstacleC+1)
                }

            }
            if(obstacleC == c_q) {
                val after = lastFreeSpaces[3]
                val before = lastFreeSpaces[7]
                if(obstacleR < r_q && obstacleR > after[0]) {
                    lastFreeSpaces[3] = arrayOf(obstacleR+1, obstacleC)
                }
                if(obstacleR > r_q && obstacleR < before[0]) {
                    lastFreeSpaces[7] = arrayOf(obstacleR-1, obstacleC)
                }
            }
        }
        return (lastFreeSpaces[0][0] - lastFreeSpaces[4][0]).absoluteValue() +
                (lastFreeSpaces[1][1] - lastFreeSpaces[5][1]).absoluteValue() +
                (lastFreeSpaces[2][1] - lastFreeSpaces[6][1]).absoluteValue() +
                (lastFreeSpaces[3][0] - lastFreeSpaces[7][0]).absoluteValue()
    }
}

fun Int.absoluteValue(): Int {
    return if(this < 0) -this else this
}
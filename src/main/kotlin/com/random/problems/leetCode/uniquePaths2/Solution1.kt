package com.random.problems.leetCode.uniquePaths2

class Solution1 {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        return uniquePathsWithObstaclesTab(obstacleGrid)
    }

    // 1. Solution: Recursive approach
    //      Time complexity:    O(2 ^ (m + n))
    //      Space complexity:   O(m * n)
    //
    fun uniquePathsWithObstaclesRecursion(obstacleGrid: Array<IntArray>): Int {
        // m x n grid
        //  0: free space, 1: obstacle
        //  robot can move to free spaces
        //  robot can move down or right
        //  how many different paths can the robot take?
        //  - in each position the robot can take:
        //      - down if it is not occupied
        //      - right if it is not occupied
        //  - how many ways can the robot make it to the end from a specific i, j position?
        //      -> this is the subproblem
        //  - from (i,j) subproblem: the number of ways is the sum of the subproblems from the
        //      upper and left squares:
        //      ways(i,j) = ways(i-1, j) + ways(i, j-1)
        //  - to get a subproblem, we have to calculate the previous two subproblems
        //      -> top-down approach
        //  - if i = m - 1 : only 1 way left
        //  - if j = n - 1 : only 1 way left
        //
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size
        return uniquePathsWithObstacles(obstacleGrid, m - 1 to n - 1)
    }

    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>, position: Pair<Int,Int>): Int {
        if (obstacleGrid[position.first][position.second] == 1) return 0
        if (position.first == 0 && position.second == 0) return 1
        // we check the previous two positions:
        val left = if(position.second > 0) uniquePathsWithObstacles(obstacleGrid, position.first to position.second - 1) else 0
        val up = if(position.first > 0) uniquePathsWithObstacles(obstacleGrid, position.first - 1 to position.second) else 0
        return left + up
    }

    // 2. Memoization:
    //  Time complexity:    O(m * n)
    //  Space complexity:   O(m * n)
    fun uniquePathsWithObstaclesMemo(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size
        return uniquePathsWithObstaclesMemo(obstacleGrid, m - 1 to n - 1, mutableMapOf())
    }

    fun uniquePathsWithObstaclesMemo(obstacleGrid: Array<IntArray>, position: Pair<Int,Int>, memo: MutableMap<Pair<Int, Int>, Int>): Int {
        if(memo.containsKey(position)) return memo[position]!!
        if (obstacleGrid[position.first][position.second] == 1) return 0
        if (position.first == 0 && position.second == 0) return 1
        // we check the previous two positions:
        val left = if(position.second > 0) uniquePathsWithObstaclesMemo(obstacleGrid, position.first to position.second - 1, memo) else 0
        val up = if(position.first > 0) uniquePathsWithObstaclesMemo(obstacleGrid, position.first - 1 to position.second, memo) else 0
        memo[position] = left + up
        return left + up
    }

    // 3. Solution: Tabulation
    //
    //  Time complexity:    O(n * m)
    //  Space complexity:   O(n * m)
    //
    fun uniquePathsWithObstaclesTab(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size

        val dp = mutableMapOf<Pair<Int,Int>,Int>()

        for(i in 0..<m) {
            for(j in 0..<n) {
                if(obstacleGrid[i][j] == 1) {
                    dp[i to j] = 0
                } else if(i == 0 && j == 0) {
                    dp[i to j] = 1
                } else {
                    val left = if(i > 0) dp[i - 1 to j]!! else 0
                    val top = if(j > 0) dp[i to j - 1]!! else 0
                    dp[i to j] = left + top
                }
            }
        }

        return dp[m - 1 to n - 1]!!
    }
}
package com.random.problems.leetCode.incrementSubmatricesByOne

class Solution1 {
    fun rangeAddQueries(n: Int, queries: Array<IntArray>): Array<IntArray> {
        return rangeAddQueries2(n, queries)
    }

    //  1. Solution
    //      TC: O(q * n ^ 2)
    //      SC: O(n ^ 2)
    fun rangeAddQueries1(n: Int, queries: Array<IntArray>): Array<IntArray> {
        val matrix = Array(n) { IntArray(n) { 0 } }

        for(query in queries) {

            for(i in query[0]..query[2]) {
                for(j in query[1]..query[3]) {
                    matrix[i][j] += 1
                }
            }
        }

        return matrix
    }

    //  2. Solution
    //      prefix sum of difference matrix
    fun rangeAddQueries2(n: Int, queries: Array<IntArray>): Array<IntArray> {
        val diffMatrix = Array(n + 1) { IntArray(n + 1) { 0 } }
        for(query in queries) {
            val startRow = query[0]
            val startCol = query[1]
            val endRow = query[2]
            val endCol = query[3]
            diffMatrix[startRow][startCol] += 1
            diffMatrix[startRow][endCol + 1] -= 1
            diffMatrix[endRow + 1][startCol] -= 1
            diffMatrix[endRow + 1][endCol + 1] += 1
        }

        val matrix = Array(n) { IntArray(n) { 0 } }
        for(i in 0..<n) {
            for(j in 0..<n) {
                val onTop = if(i == 0) 0 else matrix[i-1][j]
                val onLeft = if(j == 0) 0 else matrix[i][j-1]
                val onTopLeft = if(i == 0 || j == 0) 0 else matrix[i-1][j-1]
                matrix[i][j] = diffMatrix[i][j] + onTop + onLeft - onTopLeft
            }
        }

        return matrix
    }
}
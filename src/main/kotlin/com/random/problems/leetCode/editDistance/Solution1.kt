package com.random.problems.leetCode.editDistance

class Solution1 {
    fun minDistance(word1: String, word2: String): Int {
        val memo = Array(word1.length) { Array<Int?>(word2.length) { null } }

        fun minDistance(i: Int, j: Int): Int {
            if(i == word1.length && j == word2.length) return 0
            if(i == word1.length) return (word2.length - j)
            if(j == word2.length) return (word1.length - i)
            if(memo[i][j] != null) return memo[i][j]!!
            if(word1[i] == word2[j]) return minDistance(i + 1, j + 1)
            val costReplace = 1 + minDistance(i + 1, j + 1)
            val costRemove = 1 + minDistance(i + 1, j)
            val costInsert = 1 + minDistance(i, j + 1)

            val min = minOf(costReplace, costRemove, costInsert)
            memo[i][j] = min
            return min
        }

        return minDistance(0,0)
    }
}
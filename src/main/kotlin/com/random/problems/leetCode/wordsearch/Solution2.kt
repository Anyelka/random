package com.random.problems.leetCode.wordsearch

class Solution2 {

    //  1. Solution: Backtracking
    //
    //      Time complexity:        O(n * m  * 4 ^ l)
    //      Space complexity:       O(n * m)
    //
    fun exist(board: Array<CharArray>, word: String): Boolean {
        val (m, n) = board.size to board[0].size
        val visited = mutableSetOf<Pair<Int,Int>>()

        fun existStartingFrom(i: Int, j: Int, word: String): Boolean {
            if(word.isEmpty()) return true
            if(visited.contains(i to j)) return false
            if(i < 0 || i > board.size - 1 || j < 0 || j > board[0].size - 1) return false
            if(board[i][j] == word[0]) {
                visited.add(i to j)
                // explore the next steps
                val nextWord = word.substring(1)
                val exists = existStartingFrom(i - 1, j, nextWord)
                        || existStartingFrom(i + 1, j, nextWord)
                        || existStartingFrom(i, j - 1, nextWord)
                        || existStartingFrom(i, j + 1, nextWord)
                visited.remove(i to j)
                return exists
            }
            return false
        }

        for(i in 0..<m) {
            for(j in 0..<n) {
                if(existStartingFrom(i, j, word)) return true
            }
        }

        return false
    }


}
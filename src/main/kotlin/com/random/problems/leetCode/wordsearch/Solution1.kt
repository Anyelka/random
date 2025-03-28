package com.random.problems.leetCode.wordsearch

import com.random.util.isIn
import com.random.util.plus

class Solution1 {

    fun exist(board: Array<CharArray>, word: String): Boolean {
        return existDFS(board, word)
    }


    /*
    * letters on 2D board:
    * adjacent (horizontal + vertical) cells can be combined to make words
    * we may only use 1 letter once
    *
    * check if given word can be made up
    * */

    /*
    * Solution 1: recursive DFS with visited list and current position
    *
    *       Time complexity:    O(n * m) * O(4 ^ L) = O(n * m * 4 ^ L)
    *       Space complexity:   O(L)
    *
    *       where   n * m are the dimensions of the board
    *               L is the length of the word
    * */
    fun existDFS(board: Array<CharArray>, word: String): Boolean {
        return board.withIndex()
            .any { (i, line) ->
                line.withIndex().any { (j, char) ->
                    char == word[0] && existDFS(board, word, i to j, mutableListOf())
                }
            }

    }

    fun existDFS(
        board: Array<CharArray>,
        word: String,
        position: Pair<Int, Int>,
        visited: MutableList<Pair<Int, Int>>
    ): Boolean {
        if (word.isEmpty()) return true
        if (!position.isIn(board) || visited.contains(position)) return false

        if (word[0] == board[position.first][position.second]) {
            visited.add(position)

            return listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1).any {
                existDFS(board, word.substring(1), position + it, visited)
            }.also {
                visited.remove(position)
            }
        }

        return false
    }
}

package com.random.problems.leetCode.surroundedRegions

import kotlin.collections.ArrayDeque

class Solution1 {
    fun solve(board: Array<CharArray>): Unit {
        solve2(board)
    }

    // 1. BFS through regions from 'O'-s on the edges
    //      TC:     O(2m) + O(2n) + O(m * n) + O(m * n) = O(m * n)
    //      SC:     O(m * n)
    private fun solve1(board: Array<CharArray>) {
        val queue = ArrayDeque<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
        val writeQueue = ArrayDeque<Pair<Int, Int>>()
        val visitedRegions = mutableListOf<Pair<Int, Int>>()

        fun getDirection(square: Pair<Int, Int>): Pair<Int, Int> {
            return when {
                square.first == 0 -> 1 to 0
                square.first == board.lastIndex -> -1 to 0
                square.second == 0 -> 0 to 1
                square.second == board[0].lastIndex -> 0 to -1
                else -> throw IllegalArgumentException("Square is not on edge: $square")
            }
        }

        fun inBound(square: Pair<Int, Int>) = square.first in board.indices && square.second in board[0].indices

        fun takeIfRegion(square: Pair<Int, Int>) =
            (square.first to square.second).takeIf { inBound(it) && board[it.first][it.second] == 'O' }

        fun addIfRegion(square: Pair<Int, Int>) = takeIfRegion(square)
            ?.let { queue.add(Pair(it.first to it.second, getDirection(square))) }

        fun addNextToQueueIfRegion(square: Pair<Int, Int>, direction: Pair<Int, Int>) = takeIfRegion(square + direction)
            ?.takeIf { !visitedRegions.contains(it) }
            ?.let { queue.add(it to direction) }

        for (i in board.indices) {
            addIfRegion(i to 0)
            addIfRegion(i to board[0].lastIndex)
        }
        for (j in board[0].indices) {
            addIfRegion(0 to j)
            addIfRegion(board.lastIndex to j)
        }

        while (queue.isNotEmpty()) {
            val (region, direction) = queue.removeFirst()
            writeQueue.add(region)
            visitedRegions.add(region)

            addNextToQueueIfRegion(region, direction)
            addNextToQueueIfRegion(region, direction.left())
            addNextToQueueIfRegion(region, direction.right())
        }

        board.indices.forEach { i -> board[0].indices.forEach { j -> board[i][j] = 'X' } }

        while (writeQueue.isNotEmpty()) {
            val region = writeQueue.removeFirst()
            board[region.first][region.second] = 'O'
        }
    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> =
        (first + other.first) to (second + other.second)

    private fun Pair<Int, Int>.left(): Pair<Int, Int> = -second to first
    private fun Pair<Int, Int>.right(): Pair<Int, Int> = second to -first

    //  2. Solution:
    //      collect valid regions with DFS
    //      TC:     O(m * n)
    //      SC:     O(m * n)
    private fun solve2(board: Array<CharArray>) {
        val validRegions = mutableSetOf<Pair<Int,Int>>()
        val visited = mutableSetOf<Pair<Int,Int>>()

        fun fill(i : Int, j: Int, path: MutableSet<Pair<Int,Int>>): Boolean {
            if(visited.contains(i to j)) return validRegions.contains(i to j)
            if(i < 0 || i > board.lastIndex || j < 0 || j > board[0].lastIndex) return false
            visited.add(i to j)
            if(board[i][j] == 'O') {
                if(path.contains(i to j)) return false
                path.add(i to j)
                val isValid = i == 0 || i == board.lastIndex || j == 0 || j == board[0].lastIndex
                        || fill(i + 1, j, path)
                        || fill(i - 1, j, path)
                        || fill(i, j + 1, path)
                        || fill(i, j - 1, path)
                if(isValid) validRegions.addAll(path)
            }
            return false
        }

        fun write(i : Int, j: Int) {
            if(board[i][j] == 'O' && !validRegions.contains(i to j)) board[i][j] = 'X'
        }

        board.indices.forEach { i -> board[i].indices.forEach { j -> fill(i, j, mutableSetOf()) } }
        board.indices.forEach { i -> board[i].indices.forEach { j -> write(i,j) } }
    }
}
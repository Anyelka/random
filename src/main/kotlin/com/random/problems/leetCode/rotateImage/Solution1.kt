package com.random.problems.leetCode.rotateImage

class Solution1 {
    //  TC: O(n ^ 2)
    //  SC: O(n ^ 2)
    fun rotate(matrix: Array<IntArray>): Unit {
        val n = matrix.lastIndex

        val visited = mutableSetOf<Pair<Int,Int>>()

        fun write(row: Int, col: Int, prev: Int?) {
            if(visited.contains(row to col)) return
            val next = matrix[row][col]
            if(prev != null) {
                matrix[row][col] = prev
                visited.add(row to col)
            }

            write(col, n - row, next)
        }

        for(i in 0..n) {
            for(j in 0..n) {
                write(i,j, null)
            }
        }
    }
}
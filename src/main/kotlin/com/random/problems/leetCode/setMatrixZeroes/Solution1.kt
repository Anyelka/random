package com.random.problems.leetCode.setMatrixZeroes

class Solution1 {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        setZeroes2(matrix)
    }

    //  Solution 1:
    //      TC:     O(m+n)
    //      SC:     O(m+n)
    private fun setZeroes1(matrix: Array<IntArray>) {
        val zeroRows = IntArray(matrix.size) { 0 }
        val zeroCols = IntArray(matrix[0].size) { 0 }
        val n = matrix.size
        val m = matrix[0].size
        (0..<n).forEach { i ->
            (0..<m).forEach { j ->
                if (matrix[i][j] == 0) {
                    zeroRows[i] = 1
                    zeroCols[j] = 1
                }
            }
        }
        (0..<n).forEach { i ->
            (0..<m).forEach { j ->
                if (zeroRows[i] == 1 || zeroCols[j] == 1) matrix[i][j] = 0
            }
        }
    }

    //  Solution 2:
    //      TC:     O(m+n)
    //      SC:     O(1)
    private fun setZeroes2(matrix: Array<IntArray>) {
        val n = matrix.size
        val m = matrix[0].size
        var firstRow0 = false
        var firstCol0 = false
        (0..<n).forEach { i ->
            (0..<m).forEach { j ->
                if (matrix[i][j] == 0) {
                    if(i == 0) firstRow0 = true
                    if(j == 0) firstCol0 = true
                    matrix[i][0] = 0
                    matrix[0][j] = 0
                }
            }
        }
        (1..<n).forEach { i ->
            (1..<m).forEach { j ->
                if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0
            }
        }
        if(firstRow0) (1..<m).forEach { matrix[0][it] = 0 }
        if(firstCol0) (1..<n).forEach { matrix[it][0] = 0 }
    }
}
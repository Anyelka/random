package com.random.problems.leetCode.search2DMatrix

class Solution1 {
    // 1. Binary search
    //  TC: O(log(n * m))
    //  SC: O(1)
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val (n, m) = matrix.size to matrix[0].size
        var i = 0
        var j = n * m - 1
        while(i <= j) {
            val mid = (j + i) / 2
            val row = mid / m
            val col = mid % m
            if(matrix[row][col] == target) {
                return true
            } else if(matrix[row][col] > target) {
                j = mid - 1
            } else {
                i = mid + 1
            }
        }
        return false
    }
}
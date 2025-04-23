package com.random.problems.leetCode.binaryTreeMaximumPathSum

abstract class Solution {
    fun convertAndFindMaxPathSum(array: Array<Int?>): Int {
        return maxPathSum(convert(array))
    }

    abstract fun convert(array: Array<Int?>): TreeNode?
    abstract fun maxPathSum(root: TreeNode?): Int
}
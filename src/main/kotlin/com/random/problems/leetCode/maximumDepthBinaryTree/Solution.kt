package com.random.problems.leetCode.maximumDepthBinaryTree

abstract class Solution {
    fun convertAndGetMaxDepth(array: Array<Int?>): Int {
        return maxDepth(convert(array))
    }

    abstract fun convert(array: Array<Int?>): TreeNode?
    abstract fun maxDepth(root: TreeNode?): Int
}
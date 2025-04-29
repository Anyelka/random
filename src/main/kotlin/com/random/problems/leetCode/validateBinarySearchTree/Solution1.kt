package com.random.problems.leetCode.validateBinarySearchTree

import com.random.util.TreeNode

class Solution1 {
    // BFS
    //  TC: O(n)
    //  SC: O(n)
    fun isValidBST(root: TreeNode?): Boolean {
        return isValid(root, null, null)
    }

    fun isValid(node: TreeNode?, min: Int?, max: Int?): Boolean {
        if(node == null) return true
        val isNumberValid = min?.let { node.`val` > it } ?: true && max?.let { node.`val` < it } ?: true
        return isNumberValid && isValid(node.left, min, node.`val`) && isValid(node.right, node.`val`, max)
    }

}

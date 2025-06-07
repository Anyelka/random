package com.random.problems.leetCode.validateBinarySearchTree

import com.random.util.TreeNode

class Solution2 {
    fun isValidBST(root: TreeNode?): Boolean {
        fun isValidBST(node: TreeNode?, min: Int?, max: Int?): Boolean {
            if(node == null) return true
            if((min != null && node.`val` <= min) || (max != null && node.`val` >= max)) return false
            val nextMin = maxOf(min ?: Int.MIN_VALUE, node.`val`)
            val nextMax = minOf(max ?: Int.MAX_VALUE, node.`val`)
            return isValidBST(node.left, min, nextMax) && isValidBST(node.right, nextMin, max)
        }
        return isValidBST(root, null, null)
    }
}
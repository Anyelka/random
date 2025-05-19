package com.random.problems.leetCode.diameterOfBinaryTree

import com.random.util.TreeNode

class Solution1 {
    //  TC: O(n)
    //  SC: O(h)
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var maxDiameter = 0

        fun maxLength(node: TreeNode?): Int {
            val leftLength = if (node?.left == null) 0 else 1 + maxLength(node.left)
            val rightLength = if (node?.right == null) 0 else 1 + maxLength(node.right)

            maxDiameter = maxOf(maxDiameter, leftLength + rightLength)
            return maxOf(leftLength, rightLength)
        }

        return maxOf(maxLength(root), maxDiameter)
    }
}
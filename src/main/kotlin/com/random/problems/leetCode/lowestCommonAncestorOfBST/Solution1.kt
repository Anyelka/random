package com.random.problems.leetCode.lowestCommonAncestorOfBST

import com.random.util.TreeNode

class Solution1 {
    //  DFS:
    //  TC: O(n ^ 2)
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (hasBothChildren(root, p, q)) {
            val left = lowestCommonAncestor(root?.left, p, q)
            val right = lowestCommonAncestor(root?.right, p, q)
            return if (left == null && right == null) root
            else left ?: right
        }
        return null
    }

    fun hasBothChildren(node: TreeNode?, p: TreeNode?, q: TreeNode?): Boolean {
        return hasChild(node, p) && hasChild(node, q)
    }

    fun hasChild(node: TreeNode?, child: TreeNode?): Boolean {
        if (node == null || child == null) return false
        return node == child || hasChild(node.left, child) || hasChild(node.right, child)
    }
}
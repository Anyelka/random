package com.random.problems.leetCode.countGoodNodesInBinaryTree

import com.random.util.TreeNode

class Solution1 {
    fun goodNodes(root: TreeNode?): Int {
        fun countGoodNodes(node: TreeNode?, max: Int): Int {
            if(node == null) return 0
            val isGoodNode = node.`val` >= max
            val nextMax = maxOf(node.`val`, max)
            return (if(isGoodNode) 1 else 0) + countGoodNodes(node.left, nextMax) + countGoodNodes(node.right, nextMax)
        }

        return countGoodNodes(root, Int.MIN_VALUE)
    }
}
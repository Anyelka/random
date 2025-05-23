package com.random.problems.leetCode.binaryTreeRightSideView

import com.random.util.TreeNode

class Solution1 {
    fun rightSideView(root: TreeNode?): List<Int> {
        return rightSideView2(root)
    }

    // 1. BFS
    //  TC: O(n)
    //  SC: O(n)
    private fun rightSideView1(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val result = mutableListOf<Int>()
        val queue = ArrayDeque<Pair<Int, TreeNode>>()

        queue.add(0 to root)
        while (queue.isNotEmpty()) {
            val (height, node) = queue.removeFirst()
            if (result.size <= height) result.add(node.`val`)
            if (node.right != null) queue.add(height + 1 to node.right!!)
            if (node.left != null) queue.add(height + 1 to node.left!!)
        }
        return result
    }

    // 2. DFS
    //  TC: O(n)
    //  SC: O(h)
    private fun rightSideView2(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        fun rightSideView(node: TreeNode?, level: Int) {
            if(node == null) return
            if(level == result.size) result.add(node.`val`)
            rightSideView(node?.right, level + 1)
            rightSideView(node?.left, level + 1)
        }
        rightSideView(root, 0)
        return result
    }
}
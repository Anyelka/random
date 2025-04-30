package com.random.problems.leetCode.kthSmallestElementInBst

import com.random.util.TreeNode

class Solution2 {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        var result = 0
        var remaining = k
        fun bfs(node: TreeNode?) {
            if(node == null) return
            bfs(node.left)
            remaining--
            if(remaining == 0) {
                result = node.`val`
                return
            }
            bfs(node.right)
        }
        bfs(root)
        return result
    }
}
package com.random.problems.leetCode.kthSmallestElementInBst

import com.random.util.TreeNode

class Solution1 {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        return kthSmallest1(root, k)
    }

    // Solution 1:
    //      - Collect elements into list:   O(n)
    //      - Sort list                     O(n * log(n))
    //      - Get k-1th element             O(1)
    //
    //      TC:     O(n * log(n))
    //      SC:     O(n)
    private fun kthSmallest1(root: TreeNode?, k: Int) = getListBFS(root).sorted()[k - 1]

    private fun getListBFS(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val queue = ArrayDeque(listOf(root))
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            result.add(current!!.`val`)
            if(current.left != null) queue.add(current.left)
            if(current.right != null) queue.add(current.right)
        }
        return result
    }

    // Solution 2
    //      - Collect elements into list with BFS:                      O(n)
    //          - first add smallest values = elements to the LEFT
    //          - then add middle value = current element
    //          - last add biggest values = elements to the RIGHT
    //      - no need to sort
    //      - get k-1th element                                         O(1)
    //
    //      TC: O(n)
    //      SC: O(n)
    private fun kthSmallest2(root: TreeNode?, k: Int) = getListDFS(root)[k - 1]

    private fun getListDFS(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        fun dfs(node: TreeNode?, result: MutableList<Int>) {
            if(node == null) return
            dfs(node.left, result)
            result.add(node.`val`)
            dfs(node.right, result)
        }
        dfs(root, result)
        return result
    }
}
package com.random.problems.leetCode.binaryTreeMaximumPathSum

class Solution2 : Solution() {
    override fun convert(array: Array<Int?>): TreeNode? {
        return Solution1().convert(array)
    }

    override fun maxPathSum(root: TreeNode?): Int {
        return maxPathSum1(root)
    }

    //
    //  TC: O(n)
    //  SC: O(h)
    private fun maxPathSum1(root: TreeNode?): Int {
        var max = Int.MIN_VALUE

        fun maxPathSumOf(node: TreeNode?): Int {
            if (node == null) return 0
            val leftPathSum = maxPathSumOf(node.left)
            val rightPathSum = maxPathSumOf(node.right)
            // 1. if our current node is the "inflexion point"
            val max1 = node.`val` + maxOf(leftPathSum, rightPathSum, leftPathSum + rightPathSum)
            max = maxOf(max, max1)
            // 2. if our current node is a node in the middle
            val max2 = node.`val` + maxOf(leftPathSum, rightPathSum, 0)
            max = maxOf(max, max2)
            return max2
        }

        maxPathSumOf(root)
        return max
    }
}
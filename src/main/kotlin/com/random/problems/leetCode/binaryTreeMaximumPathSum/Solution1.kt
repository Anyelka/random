package com.random.problems.leetCode.binaryTreeMaximumPathSum

class Solution1 : Solution() {
    override fun convert(array: Array<Int?>): TreeNode? {
        val numbers = ArrayDeque<Int?>(array.toList())

        if (array.isEmpty()) return null
        val root = TreeNode(numbers.removeFirst()!!)
        val nodes = ArrayDeque<TreeNode>().also { it.add(root) }

        while (numbers.isNotEmpty()) {
            val parent = nodes.removeFirst()
            val leftValue = numbers.removeFirst()
            if (leftValue != null) {
                val leftNode = TreeNode(leftValue)
                parent.left = leftNode
                nodes.add(leftNode)
            }

            if (numbers.isNotEmpty()) {
                val rightValue = numbers.removeFirst()
                if (rightValue != null) {
                    val rightNode = TreeNode(rightValue)
                    parent.right = rightNode
                    nodes.add(rightNode)
                }
            }
        }
        return root
    }

    override fun maxPathSum(root: TreeNode?): Int {
        return this.maxPathSum2(root)
    }

    // Solution 1:
    //  max path sum of a node =
    //      1. if we include the parent: we can only take either left or right path
    //      2. if we don't include the parent: we can combine left and right or take either left or right
    //
    //      max OF:
    //          1. if the parent was included:
    //              max OF:
    //              a. taking the node:
    //              - sum + value + 0       - stopping here
    //              - sum + value + max path sum of left (includeParent)
    //              - sum + value + max path sum of right (includeParent)
    //              b. not taking the node:
    //              - sum   (calculated so far)
    //          2. if the parent was not included:
    //              (sum will be 0)
    //              max OF:
    //              a. taking the node:
    //              - value + max path sum of left (includeParent)
    //              - value + max path sum of right (includeParent)
    //              - value + max path sum of left (includeParent) + max path sum of right (includeParent)
    //              b. not taking the node:
    //              - max path sum of left
    //              - max path sum of right
    //
    //      TC:         O(n)
    //      SC:         O(h)
    //          - where h is the height of the tree
    private fun maxPathSum1(node: TreeNode?): Int {
        return this.maxPathSum1(node, false)
    }

    private fun maxPathSum1(node: TreeNode?, isParentIncluded: Boolean): Int {
        if (node == null) return 0
        if (isParentIncluded) {
            return maxOf(
                0,
                node.`val` + maxPathSum1(node.left, true),
                node.`val` + maxPathSum1(node.right, true))
        } else {
            val leftPathSum = maxPathSum1(node.left, true)
            val rightPathSum = maxPathSum1(node.right, true)
            val taken = node.`val` + maxOf(leftPathSum, rightPathSum, leftPathSum + rightPathSum)
            val notTaken = listOfNotNull(
                node.left?.let { maxPathSum1(it, false) },
                node.right?.let { maxPathSum1(it, false) }
            ).maxOrNull() ?: 0
            return if (node.right == null && node.left == null) taken else maxOf(taken, notTaken)
        }
    }

    // Solution 2:
    //  instead of keeping track of whether we included the previous node, we keep track of the max path sum so far and:
    //      for each node we:
    //      - calculate a "crossing" path (which goes through left -> self -> right)
    //          -> update the max path sum if the crossing path's sum is greater than it
    //      - calculate the max path sum without "crossing"
    //          -> return this value so the parent could use it for its own path sum
    //
    //      TC: O(n)
    //      SC: O(h)
    private fun maxPathSum2(node: TreeNode?): Int {
        val max = IntArray(1) { node!!.`val` }
        val rootMax = maxPathSum2(node, max)
        return maxOf(max[0], rootMax)
    }

    private fun maxPathSum2(node: TreeNode?, max: IntArray): Int {
        if(node == null) return 0
        val nullableLeft: Int? = node.left?.let { maxPathSum2(it, max) }
        val nullableRight: Int? = node.right?.let { maxPathSum2(it, max) }
        val left = nullableLeft ?: 0
        val right = nullableRight ?: 0
        val currentMax = listOfNotNull(node.`val` + left + right, nullableLeft, nullableRight).max()
        max[0] = maxOf(max[0], currentMax)
        return node.`val` + maxOf(0, left, right)
    }

}
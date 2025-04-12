package com.random.problems.leetCode.maximumDepthBinaryTree

class Solution1: Solution() {
    override fun convert(array: Array<Int?>): TreeNode? {
        if(array.isEmpty()) return null

        val numbers = ArrayDeque<Int?>().also { it.addAll(array) }

        val root = TreeNode(numbers.removeFirst()!!)
        val parentNodes = ArrayDeque<TreeNode>().also { it.add(root) }

        while(numbers.isNotEmpty()) {
            val parentNode = parentNodes.removeFirst()

            val leftNumber = numbers.removeFirst()
            if(leftNumber != null) {
                parentNode.left = TreeNode(leftNumber)
                parentNodes.add(parentNode.left!!)
            }
            if(numbers.isNotEmpty()) {
                val rightNumber = numbers.removeFirst()
                if(rightNumber != null) {
                    parentNode.right = TreeNode(rightNumber)
                    parentNodes.add(parentNode.right!!)
                }
            }
        }
        return root
    }

    override fun maxDepth(root: TreeNode?): Int {
        if(root == null) return 0
        return maxOf(1 + maxDepth(root.left), 1 + maxDepth(root.right))
    }
}
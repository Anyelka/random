package com.random.problems.leetCode.invertBinaryTree

class Solution2: Solution() {
    override fun invertTree(root: TreeNode?): TreeNode? {
        if(root != null) {
            val left = root.left
            val right = root.right
            root.right = left
            root.left = right
            invertTree(root.right)
            invertTree(root.left)
        }
        return root
    }

    override fun convert(array: Array<Int?>): TreeNode? {
        if(array.isEmpty()) return null

        val numbers = ArrayDeque<Int?>()
        numbers.addAll(array)

        val nodes = ArrayDeque<TreeNode?>()
        val root = TreeNode(numbers.removeFirst()!!)
        nodes.add(root)

        while(numbers.isNotEmpty()) {
            val parent = nodes.removeFirst()
            val leftValue = numbers.removeFirst()
            if(leftValue != null) {
                val leftNode = TreeNode(leftValue)
                parent!!.left = leftNode
                nodes.add(leftNode)
            }
            if(numbers.isNotEmpty()) {
                val rightValue = numbers.removeFirst()
                if(rightValue != null) {
                    val rightNode = TreeNode(rightValue)
                    parent!!.right = rightNode
                    nodes.add(rightNode)
                }
            }
        }

        return root
    }

    override fun convertBack(root: TreeNode?): Array<Int?> {
        if(root == null) return emptyArray()

        val queue = ArrayDeque<TreeNode?>()
        queue.add(root)

        val list = mutableListOf<Int?>()
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if(node != null) {
                list.add(node?.`val`)
                queue.add(node.left)
                queue.add(node.right)
            }
        }
        return list.toTypedArray()
    }

}
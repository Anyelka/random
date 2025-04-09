package com.random.problems.leetCode.invertBinaryTree

class Solution1 {
    private fun invert(node: TreeNode?): TreeNode? {
        if(node != null) {
            val temp = node.left
            node.left = node.right
            node.right = temp
            invert(node.left)
            invert(node.right)
        }
        return node
    }

    fun convertBack(root: TreeNode?) = TreeConverter.convertBack(root)

    fun convert(array: Array<Int?>): TreeNode? = TreeConverter.convert(array)
}

// The tree converter converts the Array of Ints to a TreeNode (root node)
//          so we can define the inputs & expected outputs as arrays
object TreeConverter {
    fun convert(array: Array<Int?>): TreeNode? {
        val values = ArrayDeque(array.toList())

        if(values.isEmpty()) return null

        val root = TreeNode(values.removeFirst()!!)
        val nodes = ArrayDeque<TreeNode?>()
        nodes.add(root)

        while(values.isNotEmpty()) {
            val parentNode = nodes.removeFirst()
            val leftValue = values.removeFirst()
            if(leftValue != null) {
                val leftNode = TreeNode(leftValue)
                parentNode!!.left = leftNode
                nodes.add(leftNode)
            }
            if(values.isNotEmpty()) {
                val rightValue = values.removeFirst()
                if(rightValue != null) {
                    val rightNode = TreeNode(rightValue)
                    parentNode!!.right = rightNode
                    nodes.add(rightNode)
                }
            }
        }

        return root
    }

    fun convertBack(tree: TreeNode?): Array<Int?> {
        val nodes = ArrayDeque<TreeNode?>()
        nodes.add(tree)

        val result = mutableListOf<Int?>()
        while(nodes.isNotEmpty()) {
            val node = nodes.removeFirst()
            if(node != null) {
                result.add(node.`val`)
                nodes.addAll(listOf(node.left, node.right))
            }
        }

        return result.toTypedArray()
    }
}
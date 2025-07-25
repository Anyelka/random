package com.random.util


fun convertArrayToTreeNode(array: Array<Int?>): TreeNode? {
    val numbers = ArrayDeque(array.toList())

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

fun convertTreeNodeToArray(root: TreeNode?): Array<Int?> {
    if(root == null) return arrayOf()

    val result = mutableListOf<Int?>()

    val queue = ArrayDeque<TreeNode?>().also { it.add(root) }

    while(queue.isNotEmpty()) {
        val node = queue.removeFirst()
        result.add(node?.`val`)
        if(node != null) {
            queue.add(node.left)
            queue.add(node.right)
        }
    }

    var i = result.lastIndex
    while(result[i] == null) i--

    return result.take(i+1).toTypedArray()
}

fun convertBack(root: ListNode?): IntArray {
    var current = root
    val list = mutableListOf<Int>()
    while(current != null) {
        list.add(current.`val`)
        current = current.next
    }
    return list.toIntArray()
}
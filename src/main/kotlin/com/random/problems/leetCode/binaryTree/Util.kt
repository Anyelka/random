package com.random.problems.leetCode.binaryTree

fun getTree(input: Array<Int?>): TreeNode? {
    return getTreeIterativeQueue(input)
}

/*
    incorrect imlpementation, as it does not take it into account that null nodes
    will not have their children in the array (as they are all null values)
 */
fun getTreeRecursive(array: Array<Int?>, i: Int, n: Int): TreeNode? {
    var node: TreeNode? = null
    if(i < n && array[i] != null) {
        node = TreeNode(array[i]!!)
        node.left = getTreeRecursive(array, 2*i+1, n)
        node.right = getTreeRecursive(array, 2*i+2, n)
    }
    return node
}

fun getTreeIterativeQueue(input: Array<Int?>): TreeNode? {
    val values = ArrayDeque<Int?>()
    values.addAll(input)

    if(values.isEmpty()) {
        return null
    }

    var root = TreeNode(values.removeFirst()!!)
    val nodes = ArrayDeque<TreeNode>()
    nodes.add(root)

    while(values.isNotEmpty()) {
        val node = nodes.removeFirst()
        val leftValue = values.removeFirst()
        if(leftValue != null) {
            node.left = TreeNode(leftValue)
            nodes.add(node.left!!)
        }
        if(values.isNotEmpty()) {
            val rightValue = values.removeFirst()
            if(rightValue != null) {
                node.right = TreeNode(rightValue)
                nodes.add(node.right!!)
            }
        }
    }
    return root
}

class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
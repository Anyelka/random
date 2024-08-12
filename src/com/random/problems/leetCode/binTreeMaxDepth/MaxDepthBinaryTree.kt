package com.random.problems.leetCode.binTreeMaxDepth

import kotlin.math.max

fun main() {
    val sol = Solution()
    val allInputs = arrayOf(
            arrayOf(1, 2, 3, null, 4, null, null, 5, 6, null, 7),
            arrayOf(3,9,20,null,null,15,7)
    )
    allInputs.forEach {
        val root: TreeNode? = getTree(it)
        val result = sol.maxDepth(root)
        println(result)
    }
}

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

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun maxDepth(root: TreeNode?): Int {
        return getDepth(root)
    }

    private fun getDepth(node: TreeNode?): Int {
        if(node == null) {
            return 0
        }
        return 1+max(getDepth(node.left), getDepth(node.right))
    }
}

class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
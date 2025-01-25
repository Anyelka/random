package com.random.problems.leetCode.binaryTree.binTreeDiameter

import com.random.problems.leetCode.binaryTree.TreeNode
import com.random.problems.leetCode.binaryTree.binTreeMaxDepth.getTree

fun main() {
    val allInputs = arrayOf(
            arrayOf(1, 2, 3, null, 4, null, null, 5, 6, null, 7),
            arrayOf(3,9,20,null,null,15,7)
    )

    allInputs.forEach { input ->
        diameterOfBinaryTree(getTree(input))
    }


}

fun diameterOfBinaryTree(root: TreeNode?): Int {
    return 0
}
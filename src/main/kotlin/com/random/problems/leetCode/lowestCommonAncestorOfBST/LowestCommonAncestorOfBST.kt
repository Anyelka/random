package com.random.problems.leetCode.lowestCommonAncestorOfBST

import com.random.util.*

fun main() {
    val input = listOf(
        (arrayOf(6,2,8,0,4,7,9,null,null,3,5) to (2 to 8)) to 6,
        (arrayOf(6,2,8,0,4,7,9,null,null,3,5) to (2 to 4)) to 2,
        (arrayOf<Int?>(2,1) to (2 to 1)) to 2
    )

    input.forEach { it.test() }
}

private fun Pair<Pair<Array<Int?>, Pair<Int, Int>>, Int>.test() {
    val (array, nodes) = this.first
    val root = convert(array)
    val p = find(root, nodes.first)
    val q = find(root, nodes.second)
    val result = Solution2().lowestCommonAncestor(root, p, q)!!.`val`
    println("Result for ${shortFormatArrayIfNeeded(array)} is: $result - ${isCorrectStringWithExpected(result, this.second)}")
}

private fun find(root: TreeNode?, value: Int): TreeNode? {
    if(root == null || root.`val` == value) return root
    else {
        val leftNode = find(root.left, value)
        if(leftNode != null) return leftNode
        val rightNode = find(root.right, value)
        if(rightNode != null) return rightNode
    }
    return null
}

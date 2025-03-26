package com.random.problems.leetCode.invertBinaryTree

import com.random.util.isCorrectStringWithExpected
import com.random.util.test

fun main() {
    val input: List<Pair<Array<Int?>, Array<Int?>>> = listOf(
        arrayOf<Int?>(4,2,7,1,3,6,9) to arrayOf(4,7,2,9,6,3,1),
        arrayOf<Int?>(2,1,3) to arrayOf(2,3,1),
        arrayOf<Int?>() to arrayOf()
    )


    input.forEach {
        val inputArray = it.first
        val operation: (Any) -> Any = { Solution1().invertTreeWithConversions(inputArray)  }
        it.test(operation)
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
package com.random.util

fun main() {
    testBinaryTreeConversion()
}

private fun testBinaryTreeConversion() {
    val binaryTreeConversionInput = listOf(
        arrayOf(3, 1, 4, 3, null, 1, 5),
        arrayOf(3, 3, null, 4, 2),
        arrayOf<Int?>(),
        arrayOf<Int?>(1),
        arrayOf(3, 1, 4, 3, null, null, 5),
        arrayOf(32,26,47,19,null,null,56,null,27),
        arrayOf(3, null, 4, null, 1, 5),
        arrayOf(3, null, 4, 1, 5),
        arrayOf(1,2,3,4,5,null,null,6,null,null,7,9,null,null,10)
    )
    binaryTreeConversionInput.forEach {
        val original = it
        val converted = convertTreeNodeToArray(convertArrayToTreeNode(it))
        println("Result for ${format(original)} is: ${format(converted)} - ${isCorrectStringWithExpected(converted, original)}")
    }
}
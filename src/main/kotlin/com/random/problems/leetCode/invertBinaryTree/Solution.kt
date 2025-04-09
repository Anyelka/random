package com.random.problems.leetCode.invertBinaryTree

abstract class Solution {
    fun invertWithConversion(array: Array<Int?>) = convertBack(invertTree(convert(array)))
    abstract fun invertTree(root: TreeNode?): TreeNode?
    abstract fun convert(array: Array<Int?>): TreeNode?
    abstract fun convertBack(root: TreeNode?): Array<Int?>
}
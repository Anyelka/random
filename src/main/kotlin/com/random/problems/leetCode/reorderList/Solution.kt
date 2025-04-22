package com.random.problems.leetCode.reorderList

abstract class Solution {
    fun convertAndReorder(input: IntArray): IntArray {
        val reordered = convert(input)
        reorderList(reordered)
        return convertBack(reordered)
    }

    abstract fun reorderList(head: ListNode?): Unit
    abstract fun convert(input: IntArray): ListNode?
    abstract fun convertBack(reordered: ListNode?): IntArray
}
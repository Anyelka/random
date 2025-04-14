package com.random.problems.leetCode.addTwoNumbers

abstract class Solution {

    fun convertAndAdd(nums1: IntArray, nums2: IntArray): IntArray {
        return convertBack(addTwoNumbers(convert(nums1), convert(nums2)))
    }

    abstract fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode?
    abstract fun convert(nums: IntArray): ListNode?
    abstract fun convertBack(node: ListNode?): IntArray
}
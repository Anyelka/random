package com.random.problems.leetCode.merge2SortedLists

import com.random.util.ListNode

class Solution1 {
    //  TC: O(n + m)
    //  SC: O(1)
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var result: ListNode? = null
        var node1 = list1
        var node2 = list2
        var node: ListNode? = null
        fun add(next: ListNode?) {
            if(result == null) {
                node = next
                result = node
            } else {
                node!!.next = next
                node = node!!.next
            }
        }
        while(node1 != null || node2 != null) {
            if((node1?.`val` ?: Int.MAX_VALUE) <= (node2?.`val` ?: Int.MAX_VALUE)) {
                val next = ListNode(node1!!.`val`)
                add(next)
                node1 = node1.next
            } else {
                val next = ListNode(node2!!.`val`)
                add(next)
                node2 = node2.next
            }
        }
        return result
    }
}
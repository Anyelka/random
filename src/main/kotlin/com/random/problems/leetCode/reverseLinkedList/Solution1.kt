package com.random.problems.leetCode.reverseLinkedList

import com.random.util.ListNode

class Solution1 {
    fun reverseList(head: ListNode?): ListNode? {
        return reverseRecursion(head)
    }

    //  TC: O(n)
    //  SC: O(1)
    private fun reverseLoop(head: ListNode?): ListNode? {
        var node = head
        var previous: ListNode? = null
        while (node?.next != null) {
            val next = node.next
            node.next = previous
            previous = node
            node = next
        }
        node?.next = previous
        return node
    }

    //  TC: O(n)
    //  SC: O(n)
    private fun reverseRecursion(head: ListNode?): ListNode? {
        return reverseRecursion(head, null)
    }

    fun reverseRecursion(node: ListNode?, previous: ListNode?): ListNode? {
        if(node == null) return null
        val next = node.next
        node.next = previous
        return reverseRecursion(next, node) ?: node
    }
}

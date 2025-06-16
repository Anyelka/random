package com.random.problems.leetCode.reverseNodesInAKGroup

import com.random.util.ListNode
import com.random.util.TreeNode
import com.random.util.copy

class Solution1 {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        return reverseKGroup2(k, head)
    }

    private fun reverseKGroup1(k: Int, head: ListNode?): ListNode? {
        fun copy(node: ListNode?): ListNode? {
            if (node == null) return null
            val copy = ListNode(node.`val`)
            copy.next = copy(node.next)
            return copy
        }

        fun reverse(head: ListNode?, i: Int): ListNode? {
            val original = copy(head)

            val first = head
            var current = head
            var prev: ListNode? = null
            var iteration = i
            while (current != null) {
                val next = current.next
                current.next = prev
                prev = current
                current = next
                if (iteration % k == 0) {
                    first?.next = reverse(next, iteration + 1)
                    return prev
                }
                iteration++
            }
            return original
        }

        return reverse(head, 1)
    }

    private fun reverseKGroup2(k: Int, head: ListNode?): ListNode? {
        var node = head
        var count = 0
        while(count < k && node != null) {
            node = node.next
            count++
        }

        if(count < k) return head

        var prev: ListNode? = null
        var current: ListNode? = head
        var next: ListNode?

        repeat(k) {
            next = current?.next
            current?.next = prev
            prev = current
            current = next
        }

        head?.next = reverseKGroup2(k, current)
        return prev
    }
}
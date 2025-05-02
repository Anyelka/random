package com.random.problems.leetCode.linkedListCycle

import com.random.util.ListNode

class Solution {
    fun hasCycle(head: ListNode?): Boolean {
        return hasCycle2(head)
    }

    //  TC: O(n)
    //  SC: O(n)
    private fun hasCycle1(head: ListNode?): Boolean {
        val visited = mutableListOf<ListNode>()
        var node = head
        while (node != null) {
            if (visited.contains(node)) return true
            visited.add(node)
            node = node.next
        }
        return false
    }

    //  TC: O(n)
    //  SC: O(1)
    private fun hasCycle2(head: ListNode?): Boolean {
        var slow = head
        var fast = head
        while(slow != null && fast != null) {
            slow = slow.next
            fast = fast.next?.next
            if(slow != null && slow == fast) return true
        }
        return false
    }
}
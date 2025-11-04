package com.random.problems.leetCode.removeNthNodeFromEndOfList

import com.random.util.ListNode

class Solution1 {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        return removeNthFromEnd2(head, n)
    }

    // 1. Solution:
    //      TC: O(n)
    //      SC: O(n)
    fun removeNthFromEnd1(head: ListNode?, n: Int): ListNode? {
        return getRemainingNodesAndAppend(head, n).second
    }

    fun getRemainingNodesAndAppend(node: ListNode?, n: Int): Pair<Int, ListNode?> {
        if(node == null) return 0 to null
        val newNode = ListNode(node.`val`)
        val (endIndex, nextNode) = getRemainingNodesAndAppend(node.next, n)
        if(endIndex + 1 == n) {
            return endIndex + 1 to nextNode
        }
        newNode.next = nextNode
        return endIndex + 1 to newNode
    }

    // 2. Solution:
    //      TC: O(n)
    //      SC: O(n)
    fun removeNthFromEnd2(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head
        remove(dummy, n)
        return dummy.next
    }

    fun remove(node: ListNode?, n: Int): Int {
        if (node == null) return 0
        val indexFromEnd = remove(node.next, n) + 1
        if (indexFromEnd == n + 1) {
            node.next = node.next?.next
        }
        return indexFromEnd
    }

}
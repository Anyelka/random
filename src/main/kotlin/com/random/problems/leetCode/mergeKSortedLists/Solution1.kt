package com.random.problems.leetCode.mergeKSortedLists

import com.random.util.ListNode

class Solution1 {
    //  TC: O(l)
    //  SC: O(k)
    //      - where l = total length of the result
    //      - k = number of linked lists
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        var result: ListNode? = null
        var current: ListNode? = null

        val currentNodes = Array(lists.size) { i -> lists[i] }

        fun getNextValue(): Int {
            val nextNode = currentNodes.withIndex().minBy { (i, node) -> node?.`val` ?: Int.MAX_VALUE }
            currentNodes[nextNode.index] = nextNode.value!!.next
            return nextNode.value!!.`val`
        }

        while(currentNodes.any { it != null }) {
            val nextValue = getNextValue()

            val next = ListNode(nextValue)
            if(result == null) {
                result = next
            } else {
                current!!.next = next
            }
            current = next
        }

        return result
    }


    //      merge just 2 lists:
    fun merge2Lists(lists: Array<ListNode?>) : ListNode? {
        var result: ListNode? = null
        var current: ListNode? = null

        var first = lists[0]
        var second = lists[1]

        fun getNextValue(): Int {
            return if(first == null) {
                (second!!.`val`).also { second = second!!.next }
            } else if(second == null || first!!.`val` <= second!!.`val`) {
                (first!!.`val`).also { first = first!!.next }
            } else {
                if(first!!.`val` <= second!!.`val`) {
                    (first!!.`val`).also { first = first!!.next }
                } else {
                    (second!!.`val`).also { second = second!!.next }
                }
            }
        }

        while(first != null || second != null) {
            val nextValue = getNextValue()

            val next = ListNode(nextValue)
            if(result == null) {
                result = next
            } else {
                current!!.next = next
            }
            current = next
        }

        return result
    }
}
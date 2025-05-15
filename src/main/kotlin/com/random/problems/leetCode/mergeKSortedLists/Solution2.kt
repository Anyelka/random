package com.random.problems.leetCode.mergeKSortedLists

import com.random.util.ListNode
import java.util.PriorityQueue

class Solution2 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        return mergeKLists2(lists)
    }

    //      TC:     O(n * k)
    //      SC:     O(k)
    private fun mergeKLists1(lists: Array<ListNode?>): ListNode? {
        var root: ListNode? = null
        var current: ListNode? = null
        val currents = lists.copyOf().filter { it != null }.toMutableList()

        fun findMinFromCurrents(): Pair<ListNode?, Int> {
            val indexedMin = currents.withIndex().minBy { (i, node) -> node?.`val` ?: Int.MIN_VALUE }
            return indexedMin.value to indexedMin.index
        }

        while (currents.isNotEmpty()) {
            val (min, minIndex) = findMinFromCurrents()
            val next = ListNode(min!!.`val`)
            if (current == null) {
                root = next
            } else {
                current.next = next
            }
            current = next
            if (min.next != null) {
                currents[minIndex] = min.next
            } else {
                currents.removeAt(minIndex)
            }
        }
        return root
    }


    //      TC:     O(n * log(k))
    //      SC:     O(k)
    private fun mergeKLists2(lists: Array<ListNode?>): ListNode? {
        var root: ListNode? = null
        var current: ListNode? = null
        val currents = PriorityQueue<ListNode>(compareBy { it.`val` })
        currents.addAll(lists.filterNotNull())

        while (currents.isNotEmpty()) {
            val min = currents.poll()
            val next = ListNode(min.`val`)
            if (current == null) {
                root = next
            } else {
                current.next = next
            }
            current = next
            min.next?.let { currents.offer(it) }
        }
        return root
    }
}
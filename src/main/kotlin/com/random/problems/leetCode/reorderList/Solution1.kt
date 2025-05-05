package com.random.problems.leetCode.reorderList

import com.random.util.ListNode

class Solution1: Solution() {
    override fun reorderList(head: ListNode?): Unit {
        reorderList1(head)
        return
    }

    // Solution1:
    //      - Convert to array
    //      - Iterate over elements in array and take elements alternatively from front and back
    //      - Create new elements and append them to list
    //
    //      TC:     O(n) + O(n / 2) = O(n)
    //      SC:     O(n)
    private fun reorderList1(head: ListNode?) {
        if(head == null) return
        val intArray = convertBack(head)
        var current = head
        var takeFromFront = false
        var i = 0
        while(i <= (intArray.size - 1) / 2) {
            if(takeFromFront) {
                current!!.next = ListNode(intArray[i])
            } else {
                if(i != intArray.size - 1 - i) {
                    current!!.next = ListNode(intArray[intArray.size - 1 - i])
                }
                i++
            }
            current = current!!.next
            takeFromFront = !takeFromFront
        }
        return
    }

    override fun convert(input: IntArray): ListNode? {
        if(input.isEmpty()) return null
        var root: ListNode? = null
        var current = root
        for(element in input) {
            val next = ListNode(element)
            if(current == null) {
                root = next
                current = root
            } else {
                current.next = next
                current = next
            }
        }
        return root
    }

    override fun convertBack(root: ListNode?): IntArray {
        var current = root
        val list = mutableListOf<Int>()
        while(current != null) {
            list.add(current.`val`)
            current = current.next
        }
        return list.toIntArray()
    }
}
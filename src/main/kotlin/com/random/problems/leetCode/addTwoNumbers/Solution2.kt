package com.random.problems.leetCode.addTwoNumbers

class Solution2: Solution() {
    //      [9, 9, 9, 9, 9, 9, 9]
    //      [9, 9, 9, 9]
    //      0   1  2  3  4  5  6
    //      [8, 9, 9, 9, 0, 0, 0, 1]
    //
    //  Solution 1:
    //      TC:     O(n)
    //      SC:     O(n)
    //          - where n is the length of the sum
    override fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var node1 = l1
        var node2 = l2

        var remainder = 0

        var root: ListNode? = null
        var current: ListNode? = null

        fun getValue(): Int {
            var sum = (node1?.`val` ?: 0) + (node2?.`val` ?: 0)
            if(remainder > 0) {
                sum += remainder
                remainder = 0
            }
            if(sum >= 10) {
                remainder = 1
                sum -= 10
            }
            return sum
        }

        while(node1 != null && node2 != null) {
            val next = ListNode(getValue())
            if(current == null) {
                current = next
                root = next
            } else {
                current.next = next
                current = next
            }

            node1 = node1.next
            node2 = node2.next
        }

        while(node1 != null) {
            val next = ListNode(getValue())
            current!!.next = next
            current = next

            node1 = node1.next
        }

        while(node2 != null) {
            val next = ListNode(getValue())
            current!!.next = next
            current = next

            node2 = node2.next
        }

        if(remainder >= 1) {
            current!!.next = ListNode(remainder)
        }

        return root
    }

    override fun convert(nums: IntArray): ListNode? {
        val numbers = ArrayDeque<Int>().also { it.addAll(nums.toTypedArray()) }
        val root = ListNode(numbers.removeFirst())
        var parent = root
        while(numbers.isNotEmpty()) {
            val next = ListNode(numbers.removeFirst())
            parent.next = next
            parent = next
        }
        return root
    }

    override fun convertBack(node: ListNode?): IntArray {
        val numbers = mutableListOf<Int>()
        var current = node
        while(current != null) {
            numbers.add(current.`val`)
            current = current.next
        }
        return numbers.toIntArray()
    }
}
package com.random.problems.leetCode.reorderList

import com.random.util.ListNode

class Solution2: Solution() {
    override fun convert(input: IntArray): ListNode? = Solution1().convert(input)

    override fun convertBack(reordered: ListNode?): IntArray = Solution1().convertBack(reordered)
    override fun reorderList(head: ListNode?): Unit {
        reorderList2(head)
    }

    //  - convert to array = O(n)
    //  - iterate over array with left and right pointers and take values alternatively = O(n)
    //  TC: O(n)
    private fun reorderList1(head: ListNode?) {
        val array = convertToArray(head)
        var i = 0
        var j = array.size - 1
        var current = head
        while (i <= j) {
            val first = array[i]
            val second = array[j]
            if (i != 0) {
                current!!.next = ListNode(first.`val`)
                current = current!!.next
            }
            if (i != j) {
                current!!.next = ListNode(second.`val`)
                current = current!!.next
            }
            i++
            j--
        }
    }

    fun convertToArray(head: com.random.util.ListNode?): Array<ListNode> {
        val list = mutableListOf<ListNode>()
        var node = head
        while(node != null) {
            list.add(node)
            node = node.next
        }
        return list.toTypedArray()
    }

    // 2. Solution:
    //  - find middle of the list with slow-fast pointers method
    //  - reverse the second half of the list = node at middle pointer
    //  - pick 1-1 element from head and reversed middle list
    //  TC: O(n)

    private fun reorderList2(head: ListNode?): Unit {
        var slow = head
        var fast = head
        while(fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        var first = head
        var second = slow.reverse()

        while(second?.next != null) {
            val temp1 = first?.next
            val temp2 = second.next

            first?.next = second
            second.next = temp1

            first = temp1
            second = temp2
        }
    }

    private fun ListNode?.reverse(): ListNode? {
        var current = this
        var previous: ListNode? = null
        while(current != null) {
            val temp = current.next
            current.next = previous
            previous = current
            if(temp == null) {
                return current
            } else {
                current = temp
            }
        }
        return null
    }
}

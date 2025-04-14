package com.random.problems.leetCode.addTwoNumbers

class Solution1: Solution() {
    override fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val queue = ArrayDeque<ListNode>()
        queue.add(l1!!)
        queue.add(l2!!)
        val nodes = ArrayDeque<ListNode>()
        var addition = 0
        while (queue.isNotEmpty()) {
            var nextNumber: Int
            val first = queue.removeFirst()
            val second = queue.removeFirstOrNull()
            nextNumber = if(second != null) (first.`val`+second.`val`+addition) else (first.`val`+addition)
            if(first.next != null) queue.add(first.next!!)
            if(second?.next != null) queue.add(second.next!!)

            if(nextNumber >= 10) {
                nextNumber-= 10
                addition = 1
            } else {
                addition = 0
            }
            val nextElement = ListNode(nextNumber)

            val last = nodes.lastOrNull()
            if(last != null) {
                last.next = nextElement
            }
            nodes.add(nextElement)

        }
        if(addition == 1) {
            val nextElement = ListNode(addition)

            val last = nodes.lastOrNull()
            if(last != null) {
                last.next = nextElement
            }
            nodes.add(nextElement)
        }
        return nodes[0]
    }

    override fun convert(nums: IntArray): ListNode? {
        return getListNode(nums.toTypedArray())
    }

    override fun convertBack(node: ListNode?): IntArray {
        return Solution2().convertBack(node)
    }

    private fun getInput(array1: Array<Int>, array2: Array<Int>): List<ListNode> {
        val firstList = getListNode(array1)
        val secondList = getListNode(array2)
        return mutableListOf(firstList, secondList)
    }

    private fun getListNode(array: Array<Int>): ListNode {
        val listNodes = mutableListOf<ListNode>()
        for(i in array.indices) {
            val elementValue = array[array.size-1-i]
            val node = ListNode(elementValue)
            if(i > 0) {
                node.next = listNodes.last()
            }
            listNodes.add(node)
        }
        return listNodes[listNodes.size-1]
    }
}
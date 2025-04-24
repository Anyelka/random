package com.random.problems.leetCode.addTwoNumbers

class Solution3: Solution() {
    override
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        //          2   4   6   9
        //          5   6   4
        //  result:
        //  sum:    7  10  10   9   1
        //  +carry: 7  10  11  10
        //  digit:  7   0   1   0
        //  carry:      1   1   1
        //
        //  load the next nodes into a queue - at each time the queue has 1 or 2 elements max
        //  keep track of the carry - can either be 1 or 0
        //  in each iteration:
        //      - add up the two numbers (or 1 if only 1 present)
        //      - add the carry
        //      - take the digit from the sum (mod it by 10)
        //      - calculate the new carry from the sum (divide it by 10)
        //  after no more numbers are left, if we have a carry, append it at the last + 1 digit

        var result: ListNode? = null
        var current: ListNode? = null

        val numbersQueue = ArrayDeque<ListNode>()
        numbersQueue.add(l1!!)
        numbersQueue.add(l2!!)

        var carry = 0

        while(numbersQueue.isNotEmpty()) {
            val first = numbersQueue.removeFirst()
            val second = numbersQueue.removeFirstOrNull()

            val sum = first!!.`val` + (second?.`val` ?: 0) + carry
            val digit = sum % 10
            carry = sum / 10

            val next = ListNode(digit)
            if(result == null) {
                result = next
            } else {
                current!!.next = next
            }
            current = next


            if(first.next != null) numbersQueue.add(first.next!!)
            if(second?.next != null) numbersQueue.add(second.next!!)
        }

        if(carry > 0) current!!.next = ListNode(1)

        return result
    }

    override fun convert(nums: IntArray) = Solution1().convert(nums)
    override fun convertBack(node: ListNode?) = Solution1().convertBack(node)
}
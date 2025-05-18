package com.random.problems.leetCode.kthLargestElementInStream

import java.util.PriorityQueue

class KthLargest1(k: Int, nums: IntArray) {

    //  TC: O(n * log(n))
    //  SC: O(n)
    val numbers = nums.sortedDescending().toMutableList()
    val k = k

    fun add(`val`: Int): Int {
        return add2(`val`)
    }

    //  TC: O(n)
    //  SC: O(1)
    private fun add1(`val`: Int): Int {
        val index = findIndex(`val`)
        numbers.add(index, `val`)
        return numbers[k - 1]
    }

    private fun findIndex(number: Int): Int {
        var i = 0
        while(i <= numbers.lastIndex && numbers[i] >= number) i++
        return i
    }

    private fun add2(`val`: Int): Int {
        var index = numbers.binarySearch(`val`, Comparator.reverseOrder())
        if(index < 0) index = -index -1
        numbers.add(index, `val`)
        return numbers[k - 1]
    }

}

class KthLargest2(private val k: Int, nums: IntArray) {
    val minHeap = PriorityQueue<Int>()

    init { for(num in nums) add(num) }

    fun add(`val`: Int): Int {
        if(minHeap.size < k) {
            minHeap.offer(`val`)
        } else if(`val` > minHeap.peek()) {
            minHeap.poll()
            minHeap.offer(`val`)
        }
        return minHeap.peek()
    }
}
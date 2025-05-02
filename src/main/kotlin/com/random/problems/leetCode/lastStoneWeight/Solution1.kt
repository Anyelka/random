package com.random.problems.leetCode.lastStoneWeight

import java.util.PriorityQueue
import kotlin.math.max

class Solution1 {
    fun lastStoneWeight(stones: IntArray): Int {
        return lastStoneWeight4(stones)
    }

    // 1. Naive solution:
    //  TC: O(n ^ 2 * log(n))
    //  SC: O(n)
    private fun lastStoneWeight1(stones: IntArray): Int {
        return lastStoneWeight1(stones.toMutableList())
    }

    private fun lastStoneWeight1(stones: MutableList<Int>): Int {
        var sortedStones = stones.toMutableList()
        while (sortedStones.size > 1) {
            sortedStones = sortedStones.sortedDescending().toMutableList()
            val (max, max2) = sortedStones.let { it[0] to it[1] }
            sortedStones.removeAt(0)
            sortedStones.removeAt(0)
            if (max > max2) sortedStones.add(max - max2)
        }
        return if (sortedStones.size == 1) sortedStones[0] else 0
    }


    // 2. Naive solution with pre-sorting
    //  TC: O(n ^ 2)
    //  SC: O(n)
    private fun lastStoneWeight2(stones: IntArray): Int {
        return lastStoneWeight2(stones.sortedDescending().toMutableList())
    }

    private fun lastStoneWeight2(stones: MutableList<Int>): Int {
        while (stones.size > 1) {
            val (max, max2) = stones.let { it[0] to it[1] }
            stones.removeAt(0)
            stones.removeAt(0)
            if (max > max2) {
                stones.insert(max - max2)
            }
        }
        return if (stones.size == 1) stones[0] else 0
    }
    private fun MutableList<Int>.insert(num: Int) {
        val position = indexOfFirst { it < num }
        if (position == -1) {
            add(num)
        } else {
            add(position, num)
        }
    }

    // 3. Naive solution with pre-sorting an BS insertion
    //  TC: O(n * log(n)) + O(n * log(n)) = O(n * log(n))
    //  SC: O(n)
    private fun lastStoneWeight3(stones: IntArray): Int {
        return lastStoneWeight3(stones.sortedDescending().toMutableList())
    }

    private fun lastStoneWeight3(stones: MutableList<Int>): Int {
        while (stones.size > 1) {
            val (max, max2) = stones.let { it[0] to it[1] }
            stones.removeAt(0)
            stones.removeAt(0)
            if (max > max2) stones.insertBS(max - max2)
        }
        return if (stones.size == 1) stones[0] else 0
    }
    private fun MutableList<Int>.insertBS(num: Int) {
        var position = binarySearch(num, Comparator.reverseOrder())
        if (position < 0) position = -position -1
        add(position, num)
    }

    // 4. Min Heap
    //  O(n * log(n))
    //  O(n)
    private fun lastStoneWeight4(stones: IntArray): Int {
        val maxHeap = PriorityQueue<Int> { a, b -> b.compareTo(a) }
        maxHeap.addAll(stones.toList())
        while(maxHeap.size > 1) {
            val max1 = maxHeap.poll()
            val max2 = maxHeap.poll()
            if(max1 - max2 > 0) maxHeap.add(max1 - max2)
        }
        return if(maxHeap.size > 0) maxHeap.poll() else 0
    }
}

package com.random.problems.leetCode.taskScheduler

import java.util.PriorityQueue

class Solution1 {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val taskOccurrenceMap = tasks.toList().groupingBy { it }.eachCount()

        val maxHeap = PriorityQueue<Int>(compareByDescending { it })

        taskOccurrenceMap.forEach { maxHeap.offer(it.value) }

        val queue = ArrayDeque<Pair<Int,Int>>()

        var time = 0

        while(maxHeap.isNotEmpty() || queue.isNotEmpty()) {
            time++
            if(maxHeap.isEmpty()) {
                time = queue.first().second
            } else {
                val remainingTaskCount = 1 + maxHeap.poll() * -1
                if(remainingTaskCount != 0) queue.add(remainingTaskCount * -1 to time + n)
            }

            if(queue.isNotEmpty() && queue.first().second == time) {
                maxHeap.offer(queue.removeFirst().first)
            }
        }

        return time
    }
}
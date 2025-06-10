package com.random.problems.leetCode.taskScheduler

import java.util.*
import kotlin.collections.ArrayDeque

class Solution2 {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        if(tasks.isEmpty()) return 0
        val taskFrequencies = mutableMapOf<Char,Int>()
        for(task in tasks) {
            taskFrequencies[task] = (taskFrequencies[task] ?: 0) + 1
        }
        val maxHeap = PriorityQueue<Int>(Comparator.comparing { -it })
        for(frequency in taskFrequencies) {
            maxHeap.offer(frequency.value)
        }

        val nextQueue = ArrayDeque<Pair<Int, Int>>()
        var intervals = 0
        while(maxHeap.isNotEmpty() || nextQueue.isNotEmpty()) {
            if(maxHeap.isNotEmpty()) {
                val nextLeft = maxHeap.poll()
                if(nextLeft > 1) nextQueue.add(nextLeft - 1 to intervals + n)
            }
            if(nextQueue.isNotEmpty() && nextQueue.first().second == intervals) {
                val nextTask = nextQueue.removeFirst()
                maxHeap.offer(nextTask.first)
            }
            intervals++
        }
        return intervals
    }
}
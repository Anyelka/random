package com.random.problems.leetCode.networkDelayTime

import java.util.PriorityQueue

class Solution2 {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val adjacencyMap = times.groupBy({ it[0] }, { it[1] to it[2] })
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { it.first }).also { it.add(0 to k) }
        val visited = mutableSetOf<Int>()

        var minTravelTime = 0
        while(minHeap.isNotEmpty()) {
            val (time, node) = minHeap.poll()
            if(visited.contains(node)) continue
            visited.add(node)

            minTravelTime = maxOf(minTravelTime, time)

            for(next in adjacencyMap[node] ?: emptyList()) {
                val (nextNode, nextTime) = next
                if(!visited.contains(nextNode)) minHeap.add(time + nextTime to nextNode)
            }
        }
        return if(visited.size == n) minTravelTime else -1
    }

}
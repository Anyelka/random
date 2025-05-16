package com.random.problems.leetCode.networkDelayTime

import java.util.*

class Solution3 {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val adjacencyList: MutableMap<Int, MutableMap<Int, Int>> = mutableMapOf()
        for(edge in times) {
            val neighbors = adjacencyList[edge[0]]
            if(neighbors == null) adjacencyList[edge[0]] = mutableMapOf(edge[1] to edge[2])
            else neighbors[edge[1]] = edge[2]
        }

        //  weighted, directed acyclic graph: Djikstra's algorithm
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        minHeap.offer(0 to k)

        val visitedNodes = mutableSetOf<Int>()

        var minTime = 0

        while(minHeap.isNotEmpty()) {
            val (time, node) = minHeap.poll()
            if(visitedNodes.contains(node)) continue
            visitedNodes.add(node)
            minTime = time
            adjacencyList[node]?.forEach {
                minHeap.offer(time + it.value to it.key)
            }
        }

        return if(visitedNodes.size == n) minTime else -1
    }
}
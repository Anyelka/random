package com.random.problems.leetCode.networkDelayTime

import java.util.PriorityQueue

class Solution4 {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        return djikstra(times, n, k)
    }

    // BFS: does not work!!!
    //  Takes too much time
    fun bfs(times: Array<IntArray>, n: Int, k: Int): Int {
        val adjacencyList = Array(n+1) { IntArray(n+1) { -1 } }
        for((source, target, weight) in times) {
            adjacencyList[source][target] = weight
        }

        val minTimes = Array(n+1) { Int.MAX_VALUE }
        val nodeQueue = ArrayDeque<Pair<Int,Int>>()

        nodeQueue.add(k to 0)

        while(nodeQueue.isNotEmpty()) {
            val (node, cost) = nodeQueue.removeFirst()

            if(minTimes[node] < cost) continue
            minTimes[node] = minOf(minTimes[node], cost)
            val neighbors = adjacencyList[node]
            for((neighbor, neighborCost) in neighbors.withIndex()) {
                if(neighborCost != -1) {
                    val nextCost = cost + neighborCost
                    nodeQueue.add(neighbor to nextCost)
                }
            }
        }

        val maxCost = minTimes.drop(1).max()
        return if(maxCost == Int.MAX_VALUE) -1 else maxCost
    }

    fun djikstra(times: Array<IntArray>, n: Int, k: Int): Int {
        val adjacencyList = Array(n+1) { IntArray(n+1) { -1 } }
        for((source, target, weight) in times) {
            adjacencyList[source][target] = weight
        }

        val minHeap = PriorityQueue<Pair<Int, Int>>(Comparator.comparing { it.first })
        minHeap.offer(0 to k)
        val visited = mutableSetOf<Int>()
        var minCost = 0
        while(minHeap.isNotEmpty()) {
            val (cost, next) = minHeap.poll()
            if(!visited.add(next)) continue
            minCost = maxOf(cost, minCost)
            for((neighbor, extraCost) in adjacencyList[next].withIndex()) {
                if(extraCost != -1) minHeap.offer(cost + extraCost to neighbor)
            }
        }

        return if(visited.size == n) minCost else -1
    }
}
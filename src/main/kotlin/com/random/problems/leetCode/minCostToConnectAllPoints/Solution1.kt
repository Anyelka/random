package com.random.problems.leetCode.minCostToConnectAllPoints

import java.util.*
import kotlin.Comparator
import kotlin.math.abs

class Solution1 {
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        return minCostConnectPoints2(points)
    }

    // 1. Prims's algorithm
    //      TC: O(n ^ 2 * log(n))
    //      SC: O(n ^ 2)
    private fun minCostConnectPoints1(points: Array<IntArray>): Int {
        val visited = mutableSetOf<IntArray>()
        val minDistanceHeap = PriorityQueue<Pair<IntArray, Int>>(Comparator.comparing { it.second })
        minDistanceHeap.offer(points[0] to 0)
        var totalDistance = 0

        while (minDistanceHeap.isNotEmpty()) {
            val (position, distance) = minDistanceHeap.poll()
            if (visited.contains(position)) continue
            visited.add(position)
            totalDistance += distance
            if (visited.size == points.size) break
            for (nextPosition in points) {
                if (visited.contains(nextPosition) || nextPosition.contentEquals(position)) continue
                val nextDistance = abs(position[0] - nextPosition[0]) + abs(position[1] - nextPosition[1])
                minDistanceHeap.offer(nextPosition to nextDistance)
            }
        }

        return totalDistance
    }

    // 1. Prims's algorithm improved
    //      TC: O(n ^ 2)
    //      SC: O(n)
    fun minCostConnectPoints2(points: Array<IntArray>): Int {
        val visited = mutableSetOf<Int>()
        val minDistanceHeap = PriorityQueue<Pair<Int,Int>>(Comparator.comparing { it.second } )
        minDistanceHeap.offer(0 to 0)
        var totalDistance = 0

        while(minDistanceHeap.isNotEmpty()) {
            val (index, distance) = minDistanceHeap.poll()
            if(index in visited) continue
            visited.add(index)
            totalDistance += distance
            if(visited.size == points.size) break
            for(nextIndex in points.indices) {
                if(nextIndex in visited) continue
                val nextDistance = abs(points[nextIndex][0] - points[index][0]) + abs(points[nextIndex][1] - points[index][1])
                minDistanceHeap.offer(nextIndex to nextDistance)
            }
        }

        return totalDistance
    }
}
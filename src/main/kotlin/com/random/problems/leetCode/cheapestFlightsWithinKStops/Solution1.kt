package com.random.problems.leetCode.cheapestFlightsWithinKStops

import java.util.PriorityQueue

class Solution1 {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        return findCheapestPrice3(n, flights, src, dst, k)
    }

    // 1. Djikstra's
    //  does not work because it can't take k into account!!!
    fun findCheapestPrice1(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val adjacencyList = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        for (flight in flights) {
            adjacencyList.getOrPut(flight[0]) { mutableListOf() }.add(flight[1] to flight[2])
        }

        val minHeap = PriorityQueue<Triple<Int,Int,Int>>(Comparator.comparing { it.second })
        minHeap.offer(Triple(src, 0,0))

        val visitedNodes = mutableSetOf<Int>()

        while(minHeap.isNotEmpty()) {
            val (current, cost, steps) = minHeap.poll()
            if(visitedNodes.contains(current)) continue
            if(current == dst) return cost
            if(steps > k) continue
            visitedNodes.add(current)
            adjacencyList[current]?.forEach { (next, price) ->
                minHeap.offer(Triple(next, cost + price, steps + 1) )
            }
        }

        return -1
    }


    // 2. BFS
    //      TC: O(k * E)
    //      SC: O(E + k * n)
    fun findCheapestPrice2(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val adjacencyList = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        for (flight in flights) {
            adjacencyList.getOrPut(flight[0]) { mutableListOf() }.add(flight[1] to flight[2])
        }

        val memo = IntArray(n + 1) { Int.MAX_VALUE }

        val queue = ArrayDeque<Triple<Int,Int,Int>>()
        queue.add(Triple(src,0,-1))

        while(queue.isNotEmpty()) {
            val (airport, price, stops) = queue.removeFirst()

            if(stops > k || price >= memo[airport]) continue
            memo[airport] = minOf(memo[airport], price)

            for((nextAirport, nextPrice) in adjacencyList[airport] ?: emptyList()) {
                queue.add(Triple(nextAirport, price + nextPrice, stops + 1))
            }
        }

        return if(memo[dst] == Int.MAX_VALUE) -1 else memo[dst]
    }

    // 3. Bellman ford algorithm
    //  TC: O((k + 1) * E)
    //  SC: O(n)
    fun findCheapestPrice3(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        var prices = IntArray(n + 1) { Int.MAX_VALUE }
        prices[src] = 0

        for(i in 0..k) {
            val nextPrices = prices.copyOf()
            for((source, destination, price) in flights) {
                if(prices[source] == Int.MAX_VALUE) continue
                nextPrices[destination] = minOf(nextPrices[destination], prices[source] + price)
            }
            prices = nextPrices
        }

        return if(prices[dst] == Int.MAX_VALUE) -1 else prices[dst]
    }
}
package com.random.problems.leetCode.cheapestFlightsWithinKStops

class Solution2 {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        var minPrices = IntArray(n + 1) { Int.MAX_VALUE }
        minPrices[src] = 0
        for(i in 0..k) {
            val nextMinPrices = minPrices.copyOf()
            for((source, destination, price) in flights) {
                if(minPrices[source] == Int.MAX_VALUE) continue
                nextMinPrices[destination] = minOf(nextMinPrices[destination], minPrices[source] + price)
            }
            minPrices = nextMinPrices
        }
        return if(minPrices[dst] == Int.MAX_VALUE) -1 else  minPrices[dst]
    }
}
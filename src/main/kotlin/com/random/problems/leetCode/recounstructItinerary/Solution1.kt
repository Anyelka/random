package com.random.problems.leetCode.recounstructItinerary

import java.util.LinkedList
import java.util.PriorityQueue

class Solution1 {
    fun findItinerary(tickets: List<List<String>>): List<String> {
        return findItinerary3(tickets)
    }

    // 1. DFS: too slow
    private fun findItinerary1(tickets: List<List<String>>): List<String> {
        val adjacencyList = mutableMapOf<String, MutableList<String>>()
        for (ticket in tickets) {
            adjacencyList.getOrPut(ticket[0]) { mutableListOf() }.add(ticket[1])
        }

        for (entry in adjacencyList.entries) {
            entry.value.sort()
        }

        val visited = mutableSetOf<Pair<String, String>>()

        val itinerary = mutableListOf<String>()

        fun dfs(airport: String): List<String>? {
            itinerary.add(airport)
            if (visited.size == tickets.size) return itinerary

            for (next in adjacencyList[airport] ?: emptyList()) {
                if (visited.contains(airport to next)) continue
                visited.add(airport to next)
                val itineraryFromNext = dfs(next)
                if (itineraryFromNext != null) return itineraryFromNext
                visited.remove(airport to next)
            }
            itinerary.removeLast()
            return null
        }

        return dfs("JFK")!!
    }

    // 2. DFS with DP:
    //
    //  TC: O(n * n!)
    //  SC: O(n * n!)
    //
    //  Runtime on leetcode: 192 ms
    private fun findItinerary2(tickets: List<List<String>>): List<String> {
        val adjacencyList = mutableMapOf<String, MutableList<String>>()
        val ticketsList = mutableListOf<Pair<String, String>>()
        for (ticket in tickets) {
            adjacencyList.getOrPut(ticket[0]) { mutableListOf() }.add(ticket[1])
            ticketsList.add(ticket[0] to ticket[1])
        }

        for (entry in adjacencyList.entries) {
            entry.value.sort()
        }

        val dp = mutableMapOf<List<Pair<String, String>>, List<String>?>()

        ticketsList.sortBy { it.first + it.second }

        fun dfs(airport: String, tickets: MutableList<Pair<String, String>>, itinerary: MutableList<String>): List<String>? {
            if(dp.containsKey(tickets)) return dp[tickets]
            itinerary.add(airport)
            if (tickets.isEmpty()) {
                return itinerary
            }

            var result: List<String>? = null
            for (next in adjacencyList[airport] ?: emptyList()) {
                if (!tickets.contains(airport to next)) continue
                val nextTickets = tickets.toMutableList()
                nextTickets.remove(airport to next)

                val itineraryFromNext = dfs(next, nextTickets, itinerary)
                if (itineraryFromNext != null) {
                    result = itineraryFromNext
                    break
                }
            }
            if(result == null) itinerary.removeLast()
            dp[tickets] = result
            return result
        }

        val itinerary = mutableListOf<String>()

        return dfs("JFK", ticketsList, itinerary)!!
    }

    // 3. Hierholzer's algorithm
    //      TC: O(E * log(E))
    //      SC: O(E + V)
    fun findItinerary3(tickets: List<List<String>>): List<String> {
        val graph = mutableMapOf<String, PriorityQueue<String>>()

        for ((from, to) in tickets) {
            graph.getOrPut(from) { PriorityQueue() }.add(to)
        }

        val result = LinkedList<String>()

        fun dfs(airport: String) {
            val destinations = graph[airport]
            while (!destinations.isNullOrEmpty()) {
                dfs(destinations.poll())
            }
            result.addFirst(airport)
        }

        dfs("JFK")
        return result
    }

    // 4. NC solution
    fun findItinerary4(tickets: List<List<String>>): List<String> {
        val adjacencyList = mutableMapOf<String, MutableList<String>>()

        for((from, to) in tickets) {
            adjacencyList.getOrPut(from) { mutableListOf() }.add(to)
        }

        adjacencyList.values.forEach { it.sort() }

        val result = mutableListOf<String>().also { it.add("JFK") }
        fun dfs(airport: String): Boolean {
            if(result.size == tickets.size + 1) return true
            if(airport !in adjacencyList) return false

            val destinations = (adjacencyList[airport]?.toList() ?: emptyList()).withIndex()
            for((index, next) in destinations) {
                adjacencyList[airport]!!.removeAt(index)
                result.add(next)

                if(dfs(next)) return true

                adjacencyList[airport]!!.add(index, next)
                result.removeLast()
            }
            return false
        }

        dfs("JFK")
        return result
    }


}
package com.random.problems.leetCode.recounstructItinerary

import com.random.util.test

fun main() {
    val input = listOf(
        listOf(listOf("MUC","LHR"),listOf("JFK","MUC"),listOf("SFO","SJC"),listOf("LHR","SFO")) to listOf("JFK","MUC","LHR","SFO","SJC"),
        listOf(listOf("JFK","SFO"),listOf("JFK","ATL"),listOf("SFO","ATL"),listOf("ATL","JFK"),listOf("ATL","SFO")) to listOf("JFK","ATL","JFK","SFO","ATL","SFO"),
        listOf(listOf("JFK","SFO"),listOf("JFK","ATL"),listOf("SFO", "LHR"),listOf("LHR","JFK"),listOf("ATL","LHR")) to listOf("JFK","ATL","LHR","JFK","SFO","LHR"),
        listOf(listOf("JFK","SFO"),listOf("JFK","ATL"),listOf("SFO","JFK"),listOf("ATL","AAA"),listOf("AAA","ATL"),listOf("ATL","BBB"),listOf("BBB","ATL")) to listOf("JFK", "SFO", "JFK", "ATL", "AAA", "ATL", "BBB", "ATL"),
        listOf(
            listOf("JFK", "SFO"),
            listOf("JFK", "ATL"),
            listOf("SFO", "JFK"),
            listOf("ATL", "AAA"),
            listOf("AAA", "ATL"),
            listOf("ATL", "BBB"),
            listOf("BBB", "ATL"),
            listOf("ATL", "CCC"),
            listOf("CCC", "ATL"),
            listOf("ATL", "DDD"),
            listOf("DDD", "ATL"),
            listOf("ATL", "EEE"),
            listOf("EEE", "ATL"),
            listOf("ATL", "FFF"),
            listOf("FFF", "ATL"),
            listOf("ATL", "GGG"),
            listOf("GGG", "ATL"),
            listOf("ATL", "HHH"),
            listOf("HHH", "ATL"),
            listOf("ATL", "III"),
            listOf("III", "ATL"),
            listOf("ATL", "JJJ"),
            listOf("JJJ", "ATL")
        ) to listOf("JFK", "SFO", "JFK", "ATL", "AAA", "ATL", "BBB", "ATL", "CCC", "ATL", "DDD", "ATL", "EEE", "ATL", "FFF", "ATL", "GGG", "ATL", "HHH", "ATL", "III", "ATL", "JJJ", "ATL"),
        listOf(listOf("JFK","SFO"),listOf("JFK","ATL"),listOf("SFO","JFK"),listOf("ATL","AAA"),listOf("AAA","ATL"),listOf("ATL","BBB"),listOf("BBB","ATL"),listOf("ATL","CCC"),listOf("CCC","ATL"),listOf("ATL","DDD"),listOf("DDD","ATL"),listOf("ATL","EEE"),listOf("EEE","ATL"),listOf("ATL","FFF"),listOf("FFF","ATL"),listOf("ATL","GGG"),listOf("GGG","ATL"),listOf("ATL","HHH"),listOf("HHH","ATL"),listOf("ATL","III"),listOf("III","ATL"),listOf("ATL","JJJ"),listOf("JJJ","ATL"),listOf("ATL","KKK"),listOf("KKK","ATL"),listOf("ATL","LLL"),listOf("LLL","ATL"),listOf("ATL","MMM"),listOf("MMM","ATL"),listOf("ATL","NNN"),listOf("NNN","ATL")) to emptyList<String>(),
        listOf(listOf("EZE","AXA"),listOf("TIA","ANU"),listOf("ANU","JFK"),listOf("JFK","ANU"),listOf("ANU","EZE"),listOf("TIA","ANU"),listOf("AXA","TIA"),listOf("TIA","JFK"),listOf("ANU","TIA"),listOf("JFK","TIA")) to listOf("JFK", "ANU", "EZE", "AXA", "TIA", "ANU", "JFK", "TIA", "ANU", "TIA", "JFK")
    )

    input.forEach {
        val tickets = it.first
        it.test { Solution1().findItinerary(tickets) }
    }
}
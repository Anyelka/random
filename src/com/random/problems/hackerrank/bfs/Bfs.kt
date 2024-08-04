package com.random.problems.hackerrank.bfs

import com.random.util.getResourceAsText
import kotlin.collections.ArrayDeque

const val FILE_PATH = "/hackerrank/bfs/TestCase0"

const val EDGE_LENGTH = 6

fun printArray(array: Array<Int>) {
    println(array.joinToString(separator =" ") + "\n")
}
fun main() {
    val lines = getResourceAsText(FILE_PATH + "_in")!!.lines()

    val q = lines[0].toInt()
    var lineIndex = 0
    for(i in 1..q) {
        lineIndex++
        val firstLine = lines[lineIndex].split(" ")
        val n = firstLine[0].toInt()
        val m = firstLine[1].toInt()
        val edges = Array<Array<Int>>(m) { Array<Int>(2) { 0 } }
        for(j in 0..< m) {
            lineIndex++
            edges[j] = lines[lineIndex].split(" ").map { it.toInt() }.toTypedArray()
        }
        lineIndex++
        val s = lines[lineIndex].toInt()

        val result = bfs(n, m, edges, s)

        printArray(result)
    }

    //val outputLines = getResourceAsText(FILE_PATH + "_out")!!.lines()

     
}

fun bfs(n: Int, m: Int, edges: Array<Array<Int>>, s: Int): Array<Int> {
    return breadthFirstSearch(n, m, edges, s)
}

fun breadthFirstSearch(nodeCount: Int, edgeCount: Int, edges: Array<Array<Int>>, startNode: Int): Array<Int> {
    val graph = getGraph(nodeCount, edges)
    return getDistances(startNode, graph).toTypedArray()
}

private fun getGraph(nodeCount: Int, edges: Array<Array<Int>>): MutableMap<Int, MutableList<Int>> {
    val graph = mutableMapOf<Int, MutableList<Int>>()
    for (node in 1..nodeCount) {
        graph[node] = mutableListOf()
    }
    for (edge in edges) {
        addEdge(edge[0], edge[1], graph)
        addEdge(edge[1], edge[0], graph)
    }
    return graph
}

fun addEdge(node: Int, otherNode: Int, graph: MutableMap<Int, MutableList<Int>>) {
    graph[node]!!.add(otherNode)
}

fun getDistances(startNode: Int, graph: MutableMap<Int, MutableList<Int>>): List<Int> {
    val result = ArrayList<Int>()
    for(i in 0..graph.size - 2) {
        result.add(-1)
    }

    val visited = mutableListOf<Int>()
    val queue = ArrayDeque<Array<Int>>()

    queue.add(arrayOf(startNode, 0))
    while(!queue.isEmpty()) {
        val node = queue.removeFirst()
        val nodeNumber = node[0]
        val nodeDistance = node[1]
        if(visited.contains(nodeNumber)) {
            continue
        }
        visited.add(nodeNumber)
        graph[nodeNumber]!!.forEach {
            queue.add(arrayOf(it, nodeDistance + 1))
        }
        if(nodeDistance != 0) {
            val nodePosition = if(nodeNumber < startNode) nodeNumber - 1 else nodeNumber - 2
            result[nodePosition] = nodeDistance * 6
        }
    }

    return result
}


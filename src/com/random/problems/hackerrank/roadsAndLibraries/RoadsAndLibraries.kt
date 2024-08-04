package com.random.problems.hackerrank.roadsAndLibraries

import com.random.util.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/hackerrank/roadsAndLibraries/TestCase4"

fun main() {
    val lines = getResourceAsText(FILE_PATH + "_in")!!.lines()
    val outputLines = getResourceAsText(FILE_PATH + "_out")!!.lines()

    val runtime = measureTime {
        var hasErrors = false
        val queries = lines[0].toInt()
        var indexDiff = 0
        for(query in 1.. queries) {
            var cityCount = 0
            var roadCount = 0
            var c_lib = 0
            var c_road = 0
            val cities = mutableListOf<IntArray>()

            val readInTime = measureTime {
                val index = query + indexDiff
                val firstLineSegments = lines[index].split(" ")

                cityCount = firstLineSegments[0].toInt()
                roadCount = firstLineSegments[1].toInt()
                c_lib = firstLineSegments[2].toInt()
                c_road = firstLineSegments[3].toInt()

                var j = 0
                while(j < roadCount) {
                    try {
                        val line = lines[index+j].split(" ")
                        cities.add(intArrayOf(line[0].toInt(), line[1].toInt()))
                        j++
                    } catch (e: IndexOutOfBoundsException) {
                        println("...")
                    }
                }
                indexDiff += roadCount
            }


            println("Time taken to read the input file: $readInTime")

            var result = 0L
            val findAlgorithmTime = measureTime {
                result = roadsAndLibrariesBfs(cityCount, c_lib, c_road, cities.map { it.toTypedArray() }.toTypedArray())
            }
            println(result)
            println("Time taken to find the cost in the graph: $findAlgorithmTime")

            val outputLine = outputLines[query-1]
            if(outputLine != result.toString()) {
                hasErrors = true
                System.err.println("$query. line is expected to be: '$outputLine', but was: '$result'")
            }
        }
        if(!hasErrors) {
            println("   =======  PERFECT SOLUTION!   =======  ")
        }
    }
    println("   =======  Time taken to run: $runtime")
}

fun roadsAndLibrariesRecursion(n: Int, c_lib: Int, c_road: Int, cities: Array<Array<Int>>): Long {
    if(c_lib <= c_road) {
        return (n.toLong() * c_lib.toLong())
    }

    var graph: MutableMap<Int, MutableList<Int>>
    val readInGraphTime = measureTime { graph = getGraph(cities, n) }
    println("Read in graph in time: $readInGraphTime")

    val nodes = mutableListOf<Int>()

    var numberOfSubGraphs = 0


    for(node in graph.keys) {
        if(nodes.contains(node)) {
            continue
        }
        addNodes(graph, node, nodes)
        numberOfSubGraphs++
    }

    var totalCost = 0L
    val costTime = measureTime { totalCost = getTotalCost(n, numberOfSubGraphs, c_lib, c_road) }
    println("Time taken to add up the total cost: $costTime")
    return totalCost
}

fun addNodes(graph: MutableMap<Int, MutableList<Int>>, node: Int, nodes: MutableList<Int>) {
    if(nodes.contains(node)) {
        return
    }
    nodes.add(node)
    graph[node]!!.forEach {
        addNodes(graph, it, nodes)
    }
}


fun roadsAndLibrariesBfs(n: Int, c_lib: Int, c_road: Int, cities: Array<Array<Int>>): Long {
    if(c_lib <= c_road) {
        return (n.toLong() * c_lib.toLong())
    }

    var graph: MutableMap<Int, MutableList<Int>>
    val readInGraphTime = measureTime { graph = getGraph(cities, n) }
    println("Read in graph in time: $readInGraphTime")

    val nodes = mutableListOf<Int>()

    var numberOfSubGraphs = 0

    val iterationTime = measureTime {
    for (node in graph.keys) {
        if(nodes.contains(node)) {
            continue
        }
        val visitedNodes: MutableList<Int> = mutableListOf()

        val queue = ArrayDeque<Int>()
        queue.add(node)

        while (!queue.isEmpty()) {
            val currentNode = queue.removeFirst()

            if (visitedNodes.contains(currentNode)) {
                continue
            }

            graph[currentNode]?.forEach {
                queue.add(it)
            }

            visitedNodes.add(currentNode)
        }

        nodes.addAll(visitedNodes)
        numberOfSubGraphs++
    } }
    println("Time taken to iterate over every node in the graph: $iterationTime")

    var totalCost = 0L
    val costTime = measureTime { totalCost = getTotalCost(n, numberOfSubGraphs, c_lib, c_road) }
    println("Time taken to add up the total cost: $costTime")
    return totalCost
}

private fun getGraph(cities: Array<Array<Int>>, n: Int): MutableMap<Int, MutableList<Int>> {
    val graph: MutableMap<Int, MutableList<Int>> = mutableMapOf()
    for (connection in cities) {
        val node1 = connection[0]
        val node2 = connection[1]
        graph.computeIfAbsent(node1) { mutableListOf() }.add(node2)
        graph.computeIfAbsent(node2) { mutableListOf() }.add(node1)
    }
    for (city in 1..n) {
        graph.computeIfAbsent(city) { mutableListOf() }
    }
    return graph
}

private fun getTotalCost(numberOfNodes: Int, numberOfSubGraphs: Int, c_lib: Int, c_road: Int): Long {
    val roadsCost = (numberOfNodes.toLong() - numberOfSubGraphs.toLong()) * c_road
    val libsCost = numberOfSubGraphs.toLong() * c_lib
    return roadsCost + libsCost
}

/*private fun getTotalCost(subGraphSizes: MutableList<MutableList<Int>>, c_lib: Int, c_road: Int): Long {
    return subGraphSizes.sumOf { ((it.size.toLong() - 1L) * c_road.toLong()) + c_lib.toLong() }
}*/

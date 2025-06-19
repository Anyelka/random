package com.random.util

fun convertAdjListToNode(adjList: Array<IntArray>): Node? {
    if (adjList.isEmpty()) return null
    val nodes = adjList.indices.map { Node(it + 1) }
    for (i in adjList.indices) {
        adjList[i].forEach { nodes[i].neighbors.add(nodes[it - 1]) }
    }
    return nodes[0]
}

fun convertNodeToAdjList(node: Node?): Array<IntArray> {
    if(node == null) return arrayOf()
    if(node.`val` != 1) {
        return convertNodeToAdjList(node.neighbors.first { it?.`val` == 1 })
    }
    val nodeMap = mutableMapOf<Int, IntArray>()
    val queue = ArrayDeque<Node>()
    val visited = mutableSetOf<Node>()
    queue.add(node)
    while(queue.isNotEmpty()) {
        val current = queue.removeFirst()
        if (!visited.add(current)) continue

        val neighbors = current.neighbors.filterNotNull()
        nodeMap[current.`val`] = neighbors.map { it.`val` }.toIntArray()

        neighbors.filter { it !in visited }.forEach { queue.add(it) }
    }

    val maxIndex = nodeMap.keys.maxOrNull() ?: 0
    return Array(maxIndex) { i -> nodeMap[i + 1] ?: intArrayOf() }
}
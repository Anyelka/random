package com.random.problems.leetCode.cloneGraph

import com.random.util.Node

class Solution1 {
    fun cloneGraph(node: Node?): Node? {
        if(node == null) return null

        val queue = ArrayDeque<Node>()
        queue.add(node)
        val processed = mutableSetOf<Node>()
        val copies = mutableMapOf<Int, Node>()

        fun getCopy(value: Int) = copies.getOrPut(value) { Node(value) }

        while(queue.isNotEmpty()) {
            val original = queue.removeFirst()
            val copy = getCopy(original.`val`)
            if(!processed.add(original)) continue
            original.neighbors.filterNotNull().forEach {
                copy.neighbors.add(getCopy(it.`val`))
                if(it !in processed) queue.add(it)
            }
        }

        return copies[1]
    }
}
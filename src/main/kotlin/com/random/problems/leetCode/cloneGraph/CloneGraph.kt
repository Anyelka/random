package com.random.problems.leetCode.cloneGraph

import com.random.util.convertAdjListToNode
import com.random.util.convertNodeToAdjList
import com.random.util.formatArrayOfIntArrays
import com.random.util.isCorrectStringWithExpectedArrayOfIntArrays

fun main() {
    val input = listOf(
        arrayOf(intArrayOf(2,4),intArrayOf(1,3),intArrayOf(2,4),intArrayOf(1,3)) to arrayOf(intArrayOf(2,4),intArrayOf(1,3),intArrayOf(2,4),intArrayOf(1,3)),
        arrayOf(intArrayOf()) to arrayOf(intArrayOf())
    )

    input.forEach {
        val adjList = it.first
        val firstNode = convertAdjListToNode(adjList)
        val result = Solution1().cloneGraph(firstNode)
        if(result == firstNode) {
            println("ERROR: References shouldn't be the same!")
        } else {
            val resultAdjList = convertNodeToAdjList(result)
            println("Result for: ${formatArrayOfIntArrays(adjList)} is: ${formatArrayOfIntArrays(resultAdjList)} - ${isCorrectStringWithExpectedArrayOfIntArrays(resultAdjList, it.second)}")
        }
    }

}
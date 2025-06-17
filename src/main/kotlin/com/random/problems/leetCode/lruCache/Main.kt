package com.random.problems.leetCode.lruCache

import com.random.util.isCorrectStringWithExpected

fun main() {
    val input = listOf(
        (arrayOf("LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get") to arrayOf(
            arrayOf(2),
            arrayOf(1, 1),
            arrayOf(2, 2),
            arrayOf(1),
            arrayOf(3, 3),
            arrayOf(2),
            arrayOf(4, 4),
            arrayOf(1),
            arrayOf(3),
            arrayOf(4)
        )) to arrayOf(null, null, null, 1, null, -1, null, -1, 3, 4),
        (arrayOf("LRUCache", "put", "put", "put", "put", "get", "get") to arrayOf(
            arrayOf(2),
            arrayOf(1, 1),
            arrayOf(2, 2),
            arrayOf(1, 10),
            arrayOf(3, 3),
            arrayOf(2),
            arrayOf(1)
        )) to arrayOf(null, null, null, null, null, -1, 10),
        arrayOf("LRUCache","get","put","get","put","put","get","get") to arrayOf(arrayOf(2),arrayOf(2),arrayOf(2,6),arrayOf(1),arrayOf(1,5),arrayOf(1,2),arrayOf(1),arrayOf(2))
        to arrayOf(null, -1, null, -1, null, null, 2, 6)
    )

    input.forEach {
        val (operationNames, operations) = it.first
        val expectedOutput = it.second

        val cache: LRUCache = LRUCache2(operations[0][0])

        val result = operationNames.indices.map { i ->
            when (operationNames[i]) {
                "put" -> {
                    cache.put(operations[i][0], operations[i][1])
                    null
                }

                "get" -> cache.get(operations[i][0])
                else -> null
            }
        }


        println("Result is: $result - ${isCorrectStringWithExpected(result, expectedOutput)}")
    }
}
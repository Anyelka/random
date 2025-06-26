package com.random.problems.leetCode.implementTrie

import com.random.util.isCorrectStringWithExpected

fun main() {
    val input = listOf(
        (arrayOf("Trie", "insert", "search", "search", "startsWith", "insert", "search") to
            arrayOf("", "apple", "apple", "app", "app", "app", "app")) to
                arrayOf(null, null, true, false, true, null, true)
    )


    input.forEach {
        val trie = Solution1()
        val (input, expectedOutput) = it
        val (operationNames, operationValues) = input
        val results = operationNames.indices.map { i -> map(operationNames, operationValues, i, trie)}
        println("Result is: $results - ${isCorrectStringWithExpected(results, expectedOutput)}")
    }
}

fun map(operationNames: Array<String>, operationValues: Array<String>, index: Int, trie: Solution1): Boolean? {
    if(index == 0) return null
    val word = operationValues[index]
    return when(operationNames[index]) {
        "insert" -> {
            trie.insert(word)
            null
        }
        "search" -> trie.search(word)
        "startsWith" -> trie.startsWith(word)
        else -> throw IllegalArgumentException("Illegal operation: ${operationNames[index]}")
    }
}
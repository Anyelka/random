package com.random.problems.leetCode.reorderList

import com.random.util.isCorrectStringWithExpected
import com.random.util.shortFormatArrayIfNeeded

fun main() {
     val input = listOf(
         intArrayOf(1,2,3,4) to intArrayOf(1,4,2,3),
         intArrayOf(1,2,3,4,5) to intArrayOf(1,5,2,4,3),
         intArrayOf(1,2) to intArrayOf(1,2),
         intArrayOf(0) to intArrayOf(0),
         intArrayOf() to intArrayOf()
     )

    input.forEach {
        val input = it.first
        val result = Solution2().convertAndReorder(input.copyOf())
        println("Result for ${shortFormatArrayIfNeeded(input)}: ${shortFormatArrayIfNeeded(result)} - ${isCorrectStringWithExpected(result, it.second)}")
    }
}
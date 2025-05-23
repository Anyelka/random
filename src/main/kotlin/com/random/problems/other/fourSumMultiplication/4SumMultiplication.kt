package com.random.problems.other.fourSumMultiplication

import com.random.problems.adventOfCode.twentyFour.util.runAndLogTime
import com.random.util.testWithSetOfSets

fun main() {
    val solutions = listOf(
        Solution1(),
        Solution2()
    )

    val input = listOf(
        intArrayOf(1, 2, 3, 6, 6, 6) to listOf(listOf(0, 1, 2, 3), listOf(0, 1, 2, 4), listOf(0, 1, 2, 5)),
        intArrayOf(1, 1, 1, 1, 1) to listOf(
            listOf(0, 1, 2, 3),
            listOf(0, 1, 2, 4),
            listOf(0, 1, 3, 4),
            listOf(0, 2, 3, 4),
            listOf(1, 2, 3, 4)
        ),
        intArrayOf(1, 2, 3, 6, 2, 6) to listOf(listOf(0, 1, 2, 3), listOf(0, 1, 2, 5), listOf(0, 2, 4, 5)),
        intArrayOf(1, 2, 3) to listOf(),
        intArrayOf() to listOf(),
        generated1(),
        generated2(),
        generated3(),
        generated4(),
        smallInput(),
        mediumInput(),
        //largeInput(),
        //varyLargeInput()
    )



    solutions.forEach { solution ->
        runAndLogTime {
            input.forEach {
                val nums = it.first
                it.testWithSetOfSets { solution.findQuadruplets(nums) }
            }
        }
    }
}
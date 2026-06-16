package com.random.problems.leetCode.networkDelayTime

import com.random.util.formatArrayOfIntArrays
import com.random.util.isCorrectStringWithExpected

fun main() {
    val input = listOf(
        ((arrayOf(intArrayOf(2,1,1),intArrayOf(2,3,1),intArrayOf(3,4,1)) to 4) to 2) to 2,
        ((arrayOf(intArrayOf(1,2,1)) to 2) to 1) to 1,
        ((arrayOf(intArrayOf(1,2,1)) to 2) to 2) to -1,
        ((arrayOf(intArrayOf(2,1,1),intArrayOf(2,3,1),intArrayOf(3,4,1),intArrayOf(2,4,1)) to 4) to 2) to 1,
        ((arrayOf(intArrayOf(2,1,1),intArrayOf(2,3,1),intArrayOf(3,4,1),intArrayOf(2,4,3)) to 4) to 2) to 2,
        ((arrayOf(intArrayOf(1,2,1), intArrayOf(2,1,3)) to 2) to 2) to 3,
        ((arrayOf(intArrayOf(2,1,4), intArrayOf(2,3,1), intArrayOf(3,1,2), intArrayOf(3,4,5), intArrayOf(1,4,2)) to 4) to 2) to 5,
        ((arrayOf(intArrayOf(1,2,1), intArrayOf(2,3,7), intArrayOf(1,3,4), intArrayOf(2,1,2)) to 3) to 2) to 6,
        bigInput()
    )


    input.forEach {
        val (input1, k) = it.first
        val (times, n) = input1
        val result = Solution4().networkDelayTime(times, n, k)

        println("Result for (${formatArrayOfIntArrays(times)}, n=$n, k=$k) is: $result - ${isCorrectStringWithExpected(result, it.second)}")
    }

}
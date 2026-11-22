package com.random.problems.leetCode.maximizeConsecutiveElementsInArrayAfterModification

import com.random.util.test
import com.random.util.testWithMeasuredTime


fun main() {
    val input = listOf(
        intArrayOf(2,1,5,1,1) to 3,
        intArrayOf(1,4,7,10) to 1,
        intArrayOf(2,7,3,3,5,4,9,1) to 7,
        intArrayOf(2,7,3,3,5,4,9,1,6) to 9,
        intArrayOf(4,6,3,4,8,6) to 6,
        intArrayOf(33,2,34,1,3) to 3,
        intArrayOf(8,10,6,12,9,12,2,3,13,19,11,18,10,16) to 8,
        intArrayOf(8,15,1,12,9,17,3,18,14,16,19,6,16,4,19,19,15,19,17) to 8,
        intArrayOf(1,2,4,5,6,8,9,10) to 6,
        intArrayOf(1,2,4,4,6,8,9,10) to 5,
    )

    input.forEach {
        val nums = it.first
        it.testWithMeasuredTime { Solution1().maxSelectedElements(nums) }
    }
}
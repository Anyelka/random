package com.random.problems.leetCode.maximumSubarraySumWithLengthDivisibleByK

import com.random.util.test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.random.Random


fun main() {
    val input = listOf(
        (intArrayOf(1,2) to 1) to 3,
        (intArrayOf(-1,-2,-3,-4,-5) to 4) to -10,
        (intArrayOf(-5,1,2,-3,4) to 2) to 4,
        (intArrayOf(-5,1,2,-3,-4) to 2) to 3,
        getBigInput1(),
        getBigInput2(),
        getTestCase655()
    )

    input.forEach {
        val (nums, k) = it.first
        (it.first to it.second.toLong()).test { Solution1().maxSubarraySum(nums, k) }
    }

}

const val filename = "maximumSubarraySumWithLengthDivisibleByK"
fun generateInput() {
    val input = IntArray(100000) { Random.nextInt(-1000000000,1000000000) }
    Files.writeString(Paths.get("${filename}.out"), "[" + input.joinToString(",") + "]")
}
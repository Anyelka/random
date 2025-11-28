package com.random.problems.leetCode.maximumSubarraySumWithLengthDivisibleByK

import com.random.util.getResourceAsText


const val INPUT_FILE_CUSTOM = "/leetcode/maximumSubarraySumWithLengthDivisibleByK/TestCase999"
const val INPUT_FILE_655 = "/leetcode/maximumSubarraySumWithLengthDivisibleByK/TestCase655"


fun getBigInput1(): Pair<Pair<IntArray, Int>, Long> = (toIntArray(INPUT_FILE_CUSTOM) to 4) to 242558739031
fun getBigInput2(): Pair<Pair<IntArray, Int>, Long> = (toIntArray(INPUT_FILE_CUSTOM) to 31) to 241953340913
fun getTestCase655(): Pair<Pair<IntArray, Int>, Long> = (toIntArray(INPUT_FILE_655) to 2) to 536L

fun toIntArray(filePath: String): IntArray =
    getResourceAsText(filePath)!!
        .replace("[", "")
        .replace("]", "")
        .split(",")
        .map { it.toInt() }
        .toIntArray()
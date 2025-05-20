package com.random.problems.leetCode.maximumSubarray

import com.random.util.getResourceAsText

const val INPUT_PATH = "/leetcode/maximumSubarray/TestCase0"
fun getBigInput() = getResourceAsText(INPUT_PATH)!!.lines()[0].split(",").map { it.toInt() }.toIntArray() to 11081
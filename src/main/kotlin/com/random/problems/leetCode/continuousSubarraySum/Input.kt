package com.random.problems.leetCode.continuousSubarraySum

import com.random.problems.leetCode.containerWithMostWater.FILE_PATH
import com.random.util.getResourceAsText

const val INPUT_98_PATH = "/leetcode/containerWithMostWater/TestCase0"

object Input {
    fun getInput98() = (getResourceAsText(INPUT_98_PATH)!!.split(",").map { it.toInt() }.toIntArray() to 2000000000) to false
}
package com.random.problems.leetCode.containerWithMostWater

import com.random.util.getResourceAsText

const val FILE_PATH = "/leetcode/containerWithMostWater/TestCase0"


fun getTestInput() = getResourceAsText(FILE_PATH)!!.split(",").map { it.toInt() }.toIntArray() to 721777500
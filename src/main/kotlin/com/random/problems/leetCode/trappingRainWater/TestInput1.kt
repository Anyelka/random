package com.random.problems.leetCode.trappingRainWater

import com.random.util.getResourceAsText

const val FILE_PATH = "/leetcode/trappingRainWater/TestCase0"

object TestInput1 {
    fun getInput(): Pair<IntArray, Int> {
        val intArray = getResourceAsText(FILE_PATH)!!.split(",").map { it.toInt() }.toIntArray()
        return intArray to 949905000
    }

}
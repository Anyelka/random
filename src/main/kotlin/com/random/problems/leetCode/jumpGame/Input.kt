package com.random.problems.leetCode.jumpGame

import com.random.util.getResourceAsText

const val FILE_PATH = "/leetcode/jumpGame/TestCase0"

fun getBigInput(): Pair<IntArray, Boolean> {
    val lines = getResourceAsText(FILE_PATH)!!.lines()
    return lines[0].split(",").map { it.toInt() }.toIntArray() to lines[1].toBoolean()
}
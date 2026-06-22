package com.random.problems.leetCode.maximumBuildingHeight

import com.random.problems.leetCode.maximizeTheMinimumPoweredCity.FILE_PATH
import com.random.util.getResourceAsText

const val BIG_INPUT_PATH = "/leetcode/maximumBuildingHeight/TestCase0"

fun bigInput(): Pair<Pair<Int, Array<IntArray>>, Int> {
    val array = getResourceAsText(FILE_PATH)!!.lines()[0]
        .replace("[[", "")
        .replace("]]", "")
        .split("],[")
        .map {
            it.split(",").map { num -> num.toInt() }.toIntArray()
        }.toTypedArray()
    return (1000000000 to array) to 10933396
}


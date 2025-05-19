package com.random.problems.leetCode.gasStation

import com.random.util.getResourceAsText

const val INPUT_GAS_FILE = "/leetcode/gasStation/TestCase0_in_gas"
const val INPUT_COST_FILE = "/leetcode/gasStation/TestCase0_in_cost"

fun getBigInput(): Pair<Pair<IntArray, IntArray>, Int> = (toIntArray(INPUT_GAS_FILE) to toIntArray(INPUT_COST_FILE)) to 99999

fun toIntArray(filePath: String): IntArray =
    getResourceAsText(filePath)!!
        .replace("[", "")
        .replace("]", "")
        .split(",")
        .map { it.toInt() }
        .toIntArray()

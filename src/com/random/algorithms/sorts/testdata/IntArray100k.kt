package com.random.algorithms.sorts.testdata


import com.random.util.getResourceAsText

const val INPUT_PATH = "/algorithms/sorts/IntArray100kInput"
const val OUTPUT_PATH = "/algorithms/sorts/IntArray100kOutput"

object IntArray100k {
    fun pair(): Pair<IntArray, IntArray> {
        return Pair(input(), expectedOutput())
    }

    fun input(): IntArray {
        return toIntArray(getResourceAsText(INPUT_PATH))
    }

    fun expectedOutput(): IntArray {
        return toIntArray(getResourceAsText(OUTPUT_PATH))
    }

    private fun toIntArray(file: String?): IntArray {
        return file?.trim()?.split(",")?.map { it.strip().toInt() }!!.toIntArray()
    }
}
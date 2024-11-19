package com.random.algorithms.sorts.testdata

import com.random.util.getResourceAsText

open class TestDataFromFile(private val inputPath: String, private val outputPath: String): TestData() {

    override fun input(): IntArray {
        return toIntArray(getResourceAsText(inputPath))
    }

    override fun expectedOutput(): IntArray {
        return toIntArray(getResourceAsText(outputPath))
    }

    private fun toIntArray(file: String?): IntArray {
        return file?.trim()?.split(",")?.map { it.strip().toInt() }!!.toIntArray()
    }
}
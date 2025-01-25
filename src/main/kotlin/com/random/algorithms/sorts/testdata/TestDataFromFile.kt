package com.random.algorithms.sorts.testdata

import com.random.util.getResourceAsText

open class TestDataFromFile(private val inputPath: String, private val outputPath: String): TestData() {

    override fun input(): Array<Int> {
        return toIntArray(getResourceAsText(inputPath))
    }

    override fun expectedOutput(): Array<Int> {
        return toIntArray(getResourceAsText(outputPath))
    }

    private fun toIntArray(file: String?): Array<Int> {
        return file?.trim()?.split(",")?.map { it.strip().toInt() }!!.toTypedArray()
    }
}
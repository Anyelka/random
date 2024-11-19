package com.random.algorithms.sorts.testdata.generate

import com.random.algorithms.sorts.kotlin.QuickSort
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    val testArray = generateRandomIntArray(100000, 1, 100000)
    Files.writeString(Paths.get("TestInputCountingSort100k"), testArray.contentToString())
    QuickSort.run(testArray)
    Files.writeString(Paths.get("TestOutputCountingSort100k"), testArray.contentToString())
}

fun generateRandomIntArray(size: Int, min: Int, max: Int): IntArray {
    return IntArray(size) { Random.nextInt(IntRange(min, max)) }
}

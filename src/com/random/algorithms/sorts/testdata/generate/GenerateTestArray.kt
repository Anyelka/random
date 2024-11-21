package com.random.algorithms.sorts.testdata.generate

import com.random.algorithms.sorts.kotlin.QuickSort
import com.random.problems.hackerrank.powerSum.powerSum
import com.random.util.pow
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    val name = "RadixSort100k"
    val testArray = generateRandomIntArray(100000, 10)
    Files.writeString(Paths.get("TestInput${name}"), testArray.contentToString())
    QuickSort.run(testArray)
    Files.writeString(Paths.get("TestOutput${name}"), testArray.contentToString())
}

fun generateRandomIntArray(size: Int, min: Int, max: Int): IntArray {
    return IntArray(size) { Random.nextInt(IntRange(min, max)) }
}

// For radix sort: for an int array the radix is always 10
fun generateRandomIntArray(size: Int, width: Int): IntArray {
    val min = 10.pow(width - 1)
    val max = 10.pow(width) - 1
    return IntArray(size) {
        Random.nextInt(min, max)
    }
}


package com.random.algorithms.sorts

import kotlin.time.measureTime

abstract class SortingAlgorithm() {
    abstract fun run(array: IntArray)
    abstract fun getName(): String

    fun test(testData: Pair<IntArray, IntArray>) {
        println("   - ${getName()} algorithm: sorting...")
        val array = testData.first.copyOf()
        val timeTaken = measureTime {
            run(array)
        }
        if(!array.contentEquals(testData.second)) {
            error("WRONG RESULT !\n")
        } else {
            print("         - Right solution")
        }
        print("     - Time taken: $timeTaken\n")
    }
}
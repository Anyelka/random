package com.random.algorithms.sorts.kotlin

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
            println(" wrong result: ${array.contentToString()}")
            //error("WRONG RESULT !\n")
        } else {
            print("         - Right solution")
        }
        print("     - Time taken: $timeTaken\n")
    }
}
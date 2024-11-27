package com.random.algorithms.sorts.kotlin

import kotlin.time.measureTime

abstract class SortingAlgorithm<T> {
    abstract fun run(array: Array<T>)
    abstract fun getName(): String

    fun test(testData: Pair<Array<T>, Array<T>>) {
        print("   - ${getName()} algorithm: sorting...")
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

abstract class IntSortingAlgorithm(): SortingAlgorithm<Int>()

abstract class StringSortingAlgorithm(): SortingAlgorithm<String>()
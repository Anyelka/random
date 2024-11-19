package com.random.algorithms.sorts.testdata

abstract class TestData {
    fun pair(): Pair<IntArray, IntArray> {
        return Pair(input(), expectedOutput())
    }

    abstract fun input(): IntArray

    abstract fun expectedOutput(): IntArray
}
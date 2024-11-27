package com.random.algorithms.sorts.testdata

abstract class TestData {
    fun pair(): Pair<Array<Int>, Array<Int>> {
        return Pair(input(), expectedOutput())
    }

    abstract fun input(): Array<Int>

    abstract fun expectedOutput(): Array<Int>
}
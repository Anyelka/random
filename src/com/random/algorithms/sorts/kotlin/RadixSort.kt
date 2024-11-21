package com.random.algorithms.sorts.kotlin

import com.random.problems.hackerrank.timeinwords.toDigits
import com.random.util.pow

class RadixSort(private val radix: Int, private val width: Int): SortingAlgorithm() {
    override fun getName(): String {
        return "Radix Sort"
    }

    override fun run(array: IntArray) {
        // Make assumptions: the incoming array's elements all have
        //      the same radix (range of digits = 'max' - 'min')
        //      and the same width (number of digits)
        for (i in 0 until width) {
            stableCountingSort(array, i, radix)
        }
    }

    private fun stableCountingSort(array: IntArray, digitIndex: Int, radix: Int) {
        val countingArray = IntArray(radix)
        for(i in array) {
            countingArray[getDigitValue(i, digitIndex, radix)]++
        }

        var addition = 0
        val adjustedCountingArray = IntArray(radix)
        for((index, value) in countingArray.withIndex()) {
            adjustedCountingArray[index] = value + addition
            addition += value
        }

        val temp = IntArray(array.size)
        for(k in array.size - 1 downTo 0) {
            temp[--adjustedCountingArray[getDigitValue(array[k], digitIndex, radix)]] = array[k]
        }

        System.arraycopy(temp, 0, array, 0, temp.size)
    }

    private fun getDigitValue(value: Int, digitIndex: Int, radix: Int): Int {
        return value / 10.pow(digitIndex) % radix
    }
}

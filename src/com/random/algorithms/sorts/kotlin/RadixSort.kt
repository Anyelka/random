package com.random.algorithms.sorts.kotlin

import com.random.util.intValue
import com.random.util.pow

abstract class RadixSort<T>(private val radix: Int, private val width: Int): SortingAlgorithm<T>() {

    override fun run(array: Array<T>) {
        // Make assumptions: the incoming array's elements all have
        //      the same radix (range of digits = 'max' - 'min')
        //      and the same width (number of digits)
        for (i in 0 until width) {
            stableCountingSort(array, i, radix)
        }
    }

    private fun stableCountingSort(array: Array<T>, digitIndex: Int, radix: Int) {
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

        val temp = createTempArray(array)
        for(k in array.size - 1 downTo 0) {
            temp[--adjustedCountingArray[getDigitValue(array[k], digitIndex, radix)]] = array[k]
        }

        System.arraycopy(temp, 0, array, 0, temp.size)
    }

    abstract fun getDigitValue(value: T, digitIndex: Int, radix: Int): Int

    abstract fun createTempArray(array: Array<T>): Array<T>
}

class IntRadixSort(private val radix: Int, private val width: Int): RadixSort<Int>(radix, width) {
    override fun getName(): String { return "Radix Sort" }
    override fun getDigitValue(value: Int, digitIndex: Int, radix: Int): Int {
        return value / 10.pow(digitIndex) % radix
    }

    override fun createTempArray(array: Array<Int>): Array<Int> {
        return Array(array.size) { 0 }
    }
}

class StringRadixSort(private val width: Int): RadixSort<String>('z'.intValue(), width) {
    override fun getName(): String { return "String Radix Sort" }

    override fun getDigitValue(value: String, digitIndex: Int, radix: Int): Int {
        /*return value.chars()*/
        return value.toCharArray()[value.length - 1 - digitIndex].intValue()
    }

    override fun createTempArray(array: Array<String>): Array<String> {
        return Array(array.size) { "" }
    }
}
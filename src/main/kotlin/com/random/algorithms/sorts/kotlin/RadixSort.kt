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

    private fun stableCountingSort(array: Array<T>, position: Int, radix: Int) {
        val countingArray = IntArray(radix)
        for(i in array) {
            countingArray[getValueAtIndex(i, position, radix)]++
        }

        var addition = 0
        val adjustedCountingArray = IntArray(radix)
        for((index, value) in countingArray.withIndex()) {
            adjustedCountingArray[index] = value + addition
            addition += value
        }

        val temp = array.copyOf()
        for(k in array.size - 1 downTo 0) {
            temp[--adjustedCountingArray[getValueAtIndex(array[k], position, radix)]] = array[k]
        }

        System.arraycopy(temp, 0, array, 0, temp.size)
    }

    abstract fun getValueAtIndex(value: T, digitIndex: Int, radix: Int): Int

}

class IntRadixSort(radix: Int, width: Int): RadixSort<Int>(radix, width) {
    override fun getName(): String { return "Radix Sort" }
    override fun getValueAtIndex(value: Int, digitIndex: Int, radix: Int): Int {
        return value / 10.pow(digitIndex) % radix
    }
}

class StringRadixSort(width: Int): RadixSort<String>('z'.intValue(), width) {
    override fun getName(): String { return "String Radix Sort" }

    override fun getValueAtIndex(value: String, digitIndex: Int, radix: Int): Int {
        return value.toCharArray()[value.length - 1 - digitIndex].intValue()
    }

}
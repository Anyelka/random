package com.random.algorithms.sorts.kotlin

import com.random.util.swap

/**
 * We split up the array into a sorted and an unsorted partition.
 * At the start, the sorted partition is empty and starts at the end of the array, and
 *      the unsorted partition is the full array.
 * We iterate over the unsorted partition and select the largest element
 * After we have the selected element, we add it to the sorted partition.
 * */
object SelectionSort: IntSortingAlgorithm() {
    private const val NAME = "Selection Sort"
    override fun getName(): String {
        return NAME
    }

    override fun run(array: Array<Int>) {
        sort(array)
    }

    fun sort(array: Array<Int>) {
        for(i in array.size - 1 downTo 0) {
            var max = array[0]
            var maxIndex = 0
            for(j in 0..i) {
                if(array[j] > max) {
                    max = array[j]
                    maxIndex = j
                }
            }
            array.swap(maxIndex, i)
        }
    }
}
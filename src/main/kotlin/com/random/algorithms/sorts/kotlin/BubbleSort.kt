package com.random.algorithms.sorts.kotlin

import com.random.util.swap

/**
 * We split up the array into a sorted and an unsorted partition.
 * At the start, the sorted partition is empty and starts at the end of the array, and
 *      the unsorted partition is the full array.
 * We iterate over the unsorted partition and compare each element with the next one and
 *      if the next element is smaller, we swap the two
 * After we iterated through the unsorted partition, the biggest number "bubbles up" to the end and
 *      it becomes the part of the sorted partition
 * */
object BubbleSort: IntSortingAlgorithm() {
    private const val NAME = "Bubble Sort"
    override fun getName(): String {
        return NAME
    }

    override fun run(array: Array<Int>) {
        sort(array)
    }

    private fun sort(array: Array<Int>) {
        for (i in array.size - 1 downTo 0) {
            for(j in 0..<i) {
                if(array[j] > array[j+1]) {
                    array.swap(j, j+1)
                }
            }
        }
    }
}

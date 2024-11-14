package com.random.algorithms.sorts

object InsertionSort: SortingAlgorithm() {
    private const val NAME = "Insertion Sort"
    override fun getName(): String {
        return NAME
    }

    override fun run(array: IntArray) {
        sort(array)
    }

    fun sort(array: IntArray) {
        for(i in 1..< array.size) {
            val newElement = array[i]
            var insertionIndex = i
            for(j in i - 1 downTo 0) {
                if(newElement < array[j]) {
                    array[j+1] = array[j]
                    insertionIndex--
                } else {
                    break
                }
            }
            array[insertionIndex] = newElement
        }
    }
}
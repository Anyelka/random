package com.random.algorithms.sorts.kotlin

object InsertionSort: SortingAlgorithm() {
    private const val NAME = "Insertion Sort"
    override fun getName(): String {
        return NAME
    }

    override fun run(array: IntArray) {
        sortV2(array)
    }

    fun sortV1(array: IntArray) {
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

    fun sortV2(array: IntArray) {
        for(i in 1..< array.size) {
            val newElement = array[i]
            var j = i
            while(j > 0 && array[j-1] > newElement) {
                array[j] = array[j-1]
                j--
            }
            array[j] = newElement
        }
    }
}
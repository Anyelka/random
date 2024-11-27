package com.random.algorithms.sorts.kotlin

object ShellSort: IntSortingAlgorithm() {
    private const val NAME = "Shell Sort"
    override fun getName(): String {
        return NAME
    }

    override fun run(array: Array<Int>) {
        sortV2(array)
    }

    private fun sortV1(array: Array<Int>) {
        var gap: Int = array.size/2
        while(gap > 0) {
            for(i in gap..< array.size) {
                val newElement = array[i]
                var j = i
                while(j >= gap) {
                    val element = array[j-gap]
                    if(newElement < element) {
                        array[j] = element
                        j -= gap
                    } else {
                        break
                    }
                }
                array[j] = newElement
            }
            gap /= 2
        }
    }

    private fun sortV2(array: Array<Int>) {
        var gap: Int = array.size/2
        while(gap > 0) {
            for(i in gap..< array.size) {
                val newElement = array[i]
                var j = i
                while(j >= gap && array[j-gap] > newElement) {
                    array[j] = array[j-gap]
                    j -= gap
                }
                array[j] = newElement
            }
            gap /= 2
        }
    }
}
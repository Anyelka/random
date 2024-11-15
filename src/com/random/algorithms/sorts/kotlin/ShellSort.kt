package com.random.algorithms.sorts.kotlin

object ShellSort: SortingAlgorithm() {
    private const val NAME = "Insertion Sort"
    override fun getName(): String {
        return NAME
    }

    override fun run(array: IntArray) {
        sort(array)
    }

    private fun sort(array: IntArray) {
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
}
package com.random.algorithms.sorts.kotlin

object CountingSort: SortingAlgorithm() {
    private const val NAME = "Counting Sort"
    override fun getName(): String {
        return NAME
    }
    override fun run(array: IntArray) {
        // Make assumptions: the incoming array's elements are between 1 and 10
        val min = 1
        val max = 100000
        countingSort(array, min, max)
    }

    private fun countingSort(array: IntArray, min: Int, max: Int) {
        var countingArray = IntArray((max-min)+1)
        for(i in array) {
            countingArray[i-min]++
        }

        var j = 0
        for((i, element) in countingArray.withIndex()) {
            var value = element
            while(value > 0) {
                array[j++] = i+min
                value--
            }
        }

    }

}
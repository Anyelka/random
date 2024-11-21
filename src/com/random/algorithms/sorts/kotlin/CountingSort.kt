package com.random.algorithms.sorts.kotlin

class CountingSort(private val min: Int, private val max: Int): SortingAlgorithm() {
    override fun getName(): String {
        return "Counting Sort"
    }
    override fun run(array: IntArray) {
        // Make assumptions: the incoming array's elements are between 'min' and 'max'
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
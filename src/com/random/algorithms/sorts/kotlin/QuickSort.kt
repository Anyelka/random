package com.random.algorithms.sorts.kotlin

object QuickSort: SortingAlgorithm() {
    private const val NAME = "Quick Sort"
    override fun getName(): String {
        return NAME
    }
    override fun run(array: IntArray) {
        sort(array)
    }

    private fun sort(array: IntArray) {
        quickSort(array, 0, array.size)
    }

    private fun quickSort(array: IntArray, start: Int, end: Int) {
        if (end - start <= 1) {
            return
        }
        val pivot = array[start]
        var i = start
        var j = end
        while(i < j) {
            while(i < j && array[--j] > pivot) {}
            array[i] = array[j]

            while(i < j && array[++i] < pivot) {}
            array[j] = array[i]
        }
        array[i] = pivot

        quickSort(array, start, i)
        quickSort(array, i+1, end)

    }

}
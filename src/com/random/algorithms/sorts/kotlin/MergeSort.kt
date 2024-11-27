package com.random.algorithms.sorts.kotlin

open class MergeSort(private val isAscending: Boolean = true): SortingAlgorithm() {

    private val name = "Descending Merge Sort"
    override fun getName(): String {
        return name
    }

    override fun run(array: IntArray) {
        mergeSort(array, 0, array.size)
    }

    private fun mergeSort(array: IntArray, start: Int, end: Int) {
        if(end - start < 2) {
            return
        }
        val midpoint = (start + end) / 2

        mergeSort(array, start, midpoint)
        mergeSort(array, midpoint, end)

        merge(array, start, midpoint, end)

    }

    private fun merge(array: IntArray, start: Int, midpoint: Int, end: Int) {
        val temp = IntArray(end - start)
        var i = start
        var j = midpoint
        var k = 0
        while(i < midpoint && j < end) {
            temp[k++] = if(if(isAscending) (array[i] <= array[j]) else array[i] >= array[j]) array[i++] else array[j++]
        }
        while(i < midpoint) {
            temp[k++] = array[i++]
        }
        while(j < end) {
            temp[k++] = array[j++]
        }
        System.arraycopy(temp, 0, array, start, k)
    }

    private fun mergeOptimized(array: IntArray, start: Int, midpoint: Int, end: Int) {
        if(if(isAscending) (array[midpoint-1] < array[midpoint]) else (array[midpoint-1] > array[midpoint])) {
            return
        }

        val temp = IntArray(end - start)
        var i = start
        var j = midpoint
        var k = 0
        while(i < midpoint && j < end) {
            temp[k++] = if(if(isAscending) (array[i] <= array[j]) else array[i] >= array[j]) array[i++] else array[j++]
        }

        System.arraycopy(array, i, array, start + k, midpoint - i)
        System.arraycopy(temp, 0, array, start, k)
    }
}

object DefaultMergeSort: MergeSort()

object DescendingMergeSort: MergeSort(false)
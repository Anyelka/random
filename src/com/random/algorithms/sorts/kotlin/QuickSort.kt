package com.random.algorithms.sorts.kotlin

object QuickSort: IntSortingAlgorithm() {
    private const val NAME = "Quick Sort"
    override fun getName(): String {
        return NAME
    }
    override fun run(array: Array<Int>) {
        sort(array)
    }


    private fun sort(array: Array<Int>) {
        quickSort(array, 0, array.size)
    }

    private fun quickSort(array: Array<Int>, start: Int, end: Int) {
        if (end - start <= 1) {
            return
        }
        val pivot = array[start]
        var i = start
        var j = end
        while(i < j) {
            while(i < j && array[--j] > pivot);
            array[i] = array[j]

            while(i < j && array[++i] < pivot);
            array[j] = array[i]
        }
        array[j] = pivot

        quickSort(array, start, j)
        quickSort(array, j+1, end)
    }

    fun quickSortList(list: MutableList<Int>) {
        quickSortList(list, 0, list.size)
    }

    private fun quickSortList(list: MutableList<Int>, start: Int, end: Int) {
        if (end - start <= 1) {
            return
        }
        val pivot = list[start]
        var i = start
        var j = end
        while(i < j) {
            while(i < j && list[--j] > pivot);
            list[i] = list[j]

            while(i < j && list[++i] < pivot);
            list[j] = list[i]
        }
        list[j] = pivot

        quickSortList(list, start, j)
        quickSortList(list, j+1, end)
    }

}
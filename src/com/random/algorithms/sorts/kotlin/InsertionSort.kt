package com.random.algorithms.sorts.kotlin

object InsertionSort: SortingAlgorithm() {
    private const val NAME = "Insertion Sort"
    override fun getName(): String {
        return NAME
    }

    override fun run(array: IntArray) {
        //sortV2(array)

        sortRecursively(array)
    }

    fun sortV1(array: IntArray) {
        for(i in 1 until array.size) {
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
        for(i in 1 until array.size) {
            val newElement = array[i]
            var j = i
            while(j > 0 && array[j-1] > newElement) {
                array[j] = array[j-1]
                j--
            }
            array[j] = newElement
        }
    }

    // 2. Coding challenge: sort recursively
    fun sortRecursively(array: IntArray) {
        sortRecursivelyV1(array, 0)
        // sortRecursivelyV2(array, array.size)
    }

    //2. a: first sort the current size array,
    //          then sort the one element bigger recursively
    fun sortRecursivelyV1(array: IntArray, end: Int) {
        insert(array, end)
        if(end == array.size - 1) {
            return
        }
        sortRecursivelyV1(array, end + 1)
    }

    //2. b: first go down recursively to the one element smaller array and
    //          only sort after the smaller array(s) are sorted
    fun sortRecursivelyV2(array: IntArray, end: Int) {
        if(end < 2) {
            return
        }
        sortRecursivelyV2(array, end - 1)
        insert(array, end - 1)
    }

    private fun insert(array: IntArray, firstUnorderedIndex: Int) {
        val newElement = array[firstUnorderedIndex]
        val j = firstUnorderedIndex - shift(array, firstUnorderedIndex, newElement)
        array[j] = newElement
    }

    private fun shift(array: IntArray, i: Int, newElement: Int): Int {
        if(i < 1 || array[i-1] <= newElement) {
            return 0
        }
        array[i] = array[i-1]
        return 1 + shift(array, i-1, newElement)
    }
}
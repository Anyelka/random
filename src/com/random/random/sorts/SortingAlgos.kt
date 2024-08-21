package com.random.random.sorts

import com.random.problems.hackerrank.bfs.printArray

fun main() {
    val array = intArrayOf(20, 35, -15, 7, -22, 1, 55)

    mergeSort(array)

    printArray(array.toTypedArray())
}

fun mergeSort(array: IntArray) {
    if(array.size <= 1) {
        return
    }
    val midIndex = array.size / 2
    val leftArray = array.slice(0..<midIndex).toIntArray()
    val rightArray = array.slice(midIndex..<array.size).toIntArray()

    mergeSort(leftArray)
    mergeSort(rightArray)

    merge(array, leftArray, rightArray)
}

fun merge(array: IntArray, leftArray: IntArray, rightArray: IntArray) {
    var i = 0
    var j = 0
    var k = 0
    while(i < leftArray.size && j < rightArray.size) {
        if(leftArray[i] < rightArray[j]) {
            array[k] = leftArray[i]
            i++
        } else {
            array[k] = rightArray[j]
            j++
        }
        k++
    }

    while(i < leftArray.size) {
        array[k] = leftArray[i]
        i++
        k++
    }
    while(j < rightArray.size) {
        array[k] = rightArray[j]
        j++
        k++
    }
}

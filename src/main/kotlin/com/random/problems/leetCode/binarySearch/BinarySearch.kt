package com.random.problems.leetCode.binarySearch

fun main() {
    val input = getInput()

    input.forEach {
        it.test()
    }
}

fun Pair<Pair<IntArray, Int>, Int>.test() {
    val solution = first.first.findIndex(first.second)
    println("Index of: ${first.second}: $solution - ${correct(solution, second)}")
}

fun correct(solution: Int, second: Int): String = if(solution == second) "correct" else "WRONG SOLUTION!!!"

fun IntArray.findIndex(num: Int): Int {
    return findIndexBinarySearchIteratively(num)
}

// Brute Force Solution: going through all the elements
//      Time complexity:    O(n)
//      Space complexity:   O(1)

fun IntArray.findIndexBruteForce(num:Int): Int {
    var index = -1
    for(i in indices) {
        if(this[i] == num) index = i
    }
    return index
}

// Brute Force Solution improved: return directly
//      Time complexity:    O(n)
//      Space complexity:   O(1)
fun IntArray.findIndexBruteForceDirectReturn(num:Int): Int {
    for(i in indices) {
        if(this[i] == num) return i
    }
    return -1
}

// Binary Search Solution: recursively
//      Time complexity:    O(log(n))
//      Space complexity:   O(log(n))

fun IntArray.findIndexBinarySearchRecursively(num: Int): Int {
    return findIndexBinarySearch(num, 0, this.size - 1)
}

fun IntArray.findIndexBinarySearch(num: Int, start: Int, end: Int): Int {
    if(start > end) return -1
    val mid = (start + end) / 2
    return if(this[mid] == num) {
        mid
    } else if(num > this[mid]) {
        findIndexBinarySearch(num, mid + 1, end)
    } else {
        findIndexBinarySearch(num, start, mid - 1)
    }
}

// Binary Search Solution: iteratively
//      Time complexity:    O(log(n))
//      Space complexity:   O(1)

fun IntArray.findIndexBinarySearchIteratively(num: Int): Int {
    var start = 0
    var end = size - 1
    while(start <= end) {
        val mid = (start + end) / 2
        if (get(mid) == num) return mid
        else if (num > get(mid)) start = mid + 1
        else end = mid - 1
    }
    return -1
}

fun getInput(): List<Pair<Pair<IntArray, Int>, Int>> {
    return listOf(
        (intArrayOf(-1,0,3,5,9,12) to 9) to 4,
        (intArrayOf(-1,0,3,5,9,12) to 2) to -1,
        (intArrayOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29) to 29) to 14,
        (intArrayOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29) to 8) to -1,
        (intArrayOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29) to 1) to 0,
        (intArrayOf(
            1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39,
            41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77,
            79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99, 101, 103, 105, 107, 109, 111,
            113, 115, 117, 119, 121, 123, 125, 127, 129, 131, 133, 135, 137, 139, 141,
            143, 145, 147, 149, 151, 153, 155, 157, 159, 161, 163, 165, 167, 169, 171,
            173, 175, 177, 179, 181, 183, 185, 187, 189, 191, 193, 195, 197, 199
        ) to 199) to 99
    )
}

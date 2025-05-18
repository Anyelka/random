package com.random.problems.leetCode.kthLargestElementInStream

import com.random.util.test

fun main() {
    val input = listOf(
        ((3 to intArrayOf(4, 5, 8, 2)) to intArrayOf(3, 5, 10, 9, 4)) to arrayOf<Int?>(null, 4, 5, 5, 8, 8),
        ((4 to intArrayOf(7, 7, 7, 7, 8, 3)) to intArrayOf(2, 10, 9, 9)) to arrayOf<Int?>(null, 7, 7, 7, 8),
    )

    input.forEach {
        val (initial, added) = it.first
        val (k, initialNumbers) = initial
        val kthLargest = KthLargest2(k, initialNumbers)
        it.test { listOf(null) + added.map { num -> kthLargest.add(num) } }
    }

}
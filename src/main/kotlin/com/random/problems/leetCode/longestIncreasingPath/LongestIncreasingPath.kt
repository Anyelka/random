package com.random.problems.leetCode.longestIncreasingPath

import com.random.util.test

fun main() {
    val input = listOf(
        arrayOf(intArrayOf(9,9,4),intArrayOf(6,6,8),intArrayOf(2,1,1)) to 4,
        arrayOf(intArrayOf(3,4,5),intArrayOf(3,2,6),intArrayOf(2,2,1)) to 4,
        arrayOf(intArrayOf(1)) to 1,
        arrayOf(intArrayOf(0,1,2,3,4,5,6,7,8,9),intArrayOf(19,18,17,16,15,14,13,12,11,10),intArrayOf(20,21,22,23,24,25,26,27,28,29),intArrayOf(39,38,37,36,35,34,33,32,31,30),intArrayOf(40,41,42,43,44,45,46,47,48,49),intArrayOf(59,58,57,56,55,54,53,52,51,50),intArrayOf(60,61,62,63,64,65,66,67,68,69),intArrayOf(79,78,77,76,75,74,73,72,71,70),intArrayOf(80,81,82,83,84,85,86,87,88,89),intArrayOf(99,98,97,96,95,94,93,92,91,90),intArrayOf(100,101,102,103,104,105,106,107,108,109),intArrayOf(119,118,117,116,115,114,113,112,111,110),intArrayOf(120,121,122,123,124,125,126,127,128,129),intArrayOf(139,138,137,136,135,134,133,132,131,130),intArrayOf(0,0,0,0,0,0,0,0,0,0)) to 140
    )


    input.forEach {
        val matrix = it.first
        it.test { Solution1().longestIncreasingPath(matrix) }
    }
}
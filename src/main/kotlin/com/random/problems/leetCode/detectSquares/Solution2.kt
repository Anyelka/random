package com.random.problems.leetCode.detectSquares

class Solution2 {
    val pointFrequencies = mutableMapOf<Pair<Int,Int>, Int>()

    fun add(point: IntArray) {
        pointFrequencies[point[0] to point[1]] = pointFrequencies.getOrDefault(point[0] to point[1], 0) + 1
    }


    fun count(point: IntArray): Int {
        val (x, y) = point

        var count = 0
        for((point, freq) in pointFrequencies.entries) {
            val (x2, y2) = point
            if(x == x2 && y != y2) {
                val a = y2 - y
                val p31 = x + a to y
                val p41 = x + a to y2

                val p32 = x - a to y
                val p42 = x - a to y2

                count += freq * pointFrequencies.getOrDefault(p31, 0) * pointFrequencies.getOrDefault(p41, 0)
                count += freq * pointFrequencies.getOrDefault(p32, 0) * pointFrequencies.getOrDefault(p42, 0)
            }
        }

        return count
    }
}
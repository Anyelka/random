package com.random.problems.leetCode.detectSquares

import java.lang.Math.abs

class Solution1 {
    val squares: MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>()

    fun add(point: IntArray) {
        val square = point[0] to point[1]
        //squares[square] = squares.getOrPut(square) { 0 } + 1
        squares.add(square)
    }

    fun count(point: IntArray): Int {
        var count = 0
        for ((i, s1) in squares.withIndex()) {
            for ((j, s2) in squares.withIndex()) {
                if (i != j) {
                    if (s1.first == s2.first) {
                        val dist = abs(s2.second - s1.second)
                        for ((k, s3) in squares.withIndex()) {
                            if (i != k && j != k) {
                                if (abs(s3.first - s1.first) == dist) {
                                    if (s3.second == s1.second) {
                                        // Check 4th square
                                        if (point[0] == s3.first && point[1] == s2.second) {
                                            count++
                                        }
                                    }
                                    if (s3.second == s2.second) {
                                        // Check 4th square
                                        if (point[0] == s3.first && point[1] == s1.second) {
                                            count++
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (s1.second == s2.second) {
                        val dist = abs(s2.first - s1.first)
                        for ((k, s3) in squares.withIndex()) {
                            if (i != k && j != k) {
                                if (abs(s3.second - s2.second) == dist) {
                                    if (s3.first == s1.first) {
                                        // Check 4th square
                                        if (point[1] == s3.second && point[0] == s2.first) {
                                            count++
                                        }

                                    }
                                    if (s3.first == s2.first) {
                                        // Check 4th square}
                                        if (point[1] == s3.second && point[0] == s1.first) {
                                            count++
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return count / 4
    }

}
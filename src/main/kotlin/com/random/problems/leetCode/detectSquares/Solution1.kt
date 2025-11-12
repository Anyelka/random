package com.random.problems.leetCode.detectSquares

import kotlin.math.abs

// 1. Solution:
//  linear solution with 3 loops: accurate but ineffective
//  TC: O(n ^ 3)
//  SC: O(1)
class Solution1 {
    val squares = mutableListOf<IntArray>()

    fun add(point: IntArray) {
        squares.add(point)
    }

    fun count(point: IntArray): Int {
        var count = 0
        for((i, square1) in squares.withIndex()) {
            val (x1, y1) = square1
            // if a query point can make a square with 3 other points,
            //      it must have at least 1 point on the same vertical and 1 point
            //      on the same horizontal axis
            //      -> it is enough to only check one of them
            if(x1 == point[0] && y1 != point[1]) {
                // they are on the same vertical axis
                val a = y1 - point[1]

                for((j, square2) in squares.withIndex()) {
                    // skip duplicates
                    if(i == j) continue
                    val (x2, y2) = square2
                    // check the next square and compare it to the previous one
                    if(y2 == y1) {
                        // check the distance with the other coordinate
                        if(abs(x2 - x1) == abs(a)) {
                            val a2 = x2 - x1

                            for((k, square3) in squares.withIndex()) {
                                // skip duplicates
                                if(k == i || k == j) continue
                                val (x3, y3) = square3

                                if(x3 == x2) {
                                    // check the distance and direction compared to previous coordinate
                                    if(y3 - y2 == -a) {
                                        count++
                                        // check if the 4th point is correct compared to the 3rd one
                                        //if()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return count
    }

}
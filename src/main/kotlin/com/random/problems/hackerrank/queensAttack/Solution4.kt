package com.random.problems.hackerrank.queensAttack

import kotlin.math.abs

class Solution4 {
    fun queensAttack(n: Int, k: Int, r_q: Int, c_q: Int, obstacles: Array<Array<Int>>): Int {
        val edge1: Array<Pair<Int, Int>> = Array(n + 2) { 0 to it }
        val edge2: Array<Pair<Int, Int>> = Array(n + 2) { n + 1 to it }
        val edge3 = Array(n + 2) { it to 0 }
        val edge4 = Array(n + 2) { it to n + 1 }
        val extendedObstacles = (obstacles.map { it[0] to it[1] } + edge1 + edge2 + edge3 + edge4).distinct()

        val directions = mutableListOf<Pair<Int, Int>>()
        for (i in -1..1) {
            for (j in -1..1) {
                if (!(i == 0 && j == 0)) {
                    directions.add(i to j)
                }
            }
        }

        return directions.sumOf { (r_q to c_q).maxStepsInDirection(it, extendedObstacles) }
    }


    private fun Pair<Int, Int>.maxStepsInDirection(direction: Pair<Int, Int>, obstacles: List<Pair<Int, Int>>): Int {
        return obstacles
            .filter { o -> isInLine(this, o, direction) }
            .minOf { o -> distanceBetween(this, o) }
    }

    private fun isInLine(queen: Pair<Int, Int>, other: Pair<Int, Int>, direction: Pair<Int, Int>): Boolean {
        val (qx, qy) = queen
        val (ox, oy) = other
        val (dx, dy) = direction

        return when {
            dx == 0 || dy == 0 -> (qx == ox && dy * (oy - qy) > 0) || (qy == oy && dx * (ox - qx) > 0)
            else -> (qx - ox == dx * dy * (qy - oy))
                    && (dx > 0 && ox > qx || dx < 0 && ox < qx)
                    && (dy > 0 && oy > qy || dy < 0 && oy < qy)
        }
    }


    private fun distanceBetween(first: Pair<Int, Int>, second: Pair<Int, Int>) =
        maxOf(abs(first.first - second.first), abs(first.second - second.second)) - 1

}
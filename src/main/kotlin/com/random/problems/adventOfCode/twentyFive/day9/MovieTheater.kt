package com.random.problems.adventOfCode.twentyFive.day9

import com.random.util.getResourceAsText
import kotlin.math.abs
import kotlin.time.measureTime


const val FILE_PATH = "/adventOfCode/2025/day9/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 9 - Part 1 solution: ${Part1.solve(parse(lines.filter { it.isNotEmpty() } ))}")
        println("AOC day 9 - Part 2 solution: ${Part2.solve(parse(lines.filter { it.isNotEmpty() } ))}")
    }
    println("       -> Time taken: $timeTaken")
}

fun parse(lines: List<String>): List<Point> {
    return lines.map{ it.split(",")}.map { Point(it[0].toInt(), it[1].toInt()) }
}

data class Point(val x: Int, val y: Int)

object Part1 {
    fun solve(points: List<Point>): Long {
        var max = -1L
        for(i in points.indices) {
            for(j in i+1..points.lastIndex) {
                val (xi, yi) = points[i]
                val (xj, yj) = points[j]
                val area = (abs(xi - xj) + 1).toLong() * (abs(yi - yj) + 1)
                max = maxOf(max, area)
            }
        }
        return max
    }
}

object Part2 {
    fun solve(points: List<Point>): Long {
        return maxArea(points)
    }

    fun compressCoordinates(points: List<Point>): Quad<IntArray, IntArray, Map<Int, Int>, Map<Int, Int>> {
        val xs = points.map { it.x }.toSortedSet().toIntArray()
        val ys = points.map { it.y }.toSortedSet().toIntArray()
        val xIndex = xs.withIndex().associate { it.value to it.index }
        val yIndex = ys.withIndex().associate { it.value to it.index }
        return Quad(xs, ys, xIndex, yIndex)
    }

    data class Quad<A,B,C,D>(val a: A, val b: B, val c: C, val d: D)

    fun rasterize(
        points: List<Point>,
        xs: IntArray,
        ys:IntArray,
        xIndexes: Map<Int, Int>,
        yIndexes: Map<Int, Int>
    ): Array<BooleanArray> {
        val (width, height) = xs.size - 1 to  ys.size - 1
        val inside = Array(width) { BooleanArray(height) }

        val verticals = Array(xs.size) { mutableListOf<Pair<Int, Int>>() }
        for (i in points.indices) {
            val (current, next) = points[i] to points[(i + 1) % points.size]
            if (current.x == next.x) {
                val xIndex = xIndexes[current.x]!!
                val yMin = minOf(current.y, next.y)
                val yMax = maxOf(current.y, next.y)
                val yMinIndex = yIndexes[yMin]!!
                val yMaxIndex = yIndexes[yMax]!!
                verticals[xIndex].add(yMinIndex to yMaxIndex)
            }
        }

        for (j in 0 until height) {
            val crossings = mutableListOf<Int>()
            for (xIndex in 0 until xs.size) {
                for ((yMinIndex, yMaxIndex) in verticals[xIndex]) {
                    if (j in yMinIndex..<yMaxIndex) crossings.add(xIndex)
                }
            }
            crossings.sort()
            for (k in 0 until crossings.size step 2) {
                val l = crossings[k]
                val r = crossings[k + 1]
                for (i in l until r) inside[i][j] = true
            }
        }

        return inside
    }

    fun prefixSum(inside: Array<BooleanArray>): Array<IntArray> {
        val (width, height) = inside.size to inside[0].size
        val prefSum = Array(height + 1) { IntArray(width + 1) }
        for (j in 0 until height) {
            var rowSum = 0
            for (i in 0 until width) {
                if (inside[i][j]) rowSum++
                prefSum[j + 1][i + 1] = prefSum[j][i + 1] + rowSum
            }
        }
        return prefSum
    }

    fun sumCells(pref: Array<IntArray>, xMin: Int, xMax: Int, yMin: Int, yMax: Int): Int {
        return pref[yMax][xMax] - pref[yMin][xMax] - pref[yMax][xMin] + pref[yMin][xMin]
    }

    fun maxArea(points: List<Point>): Long {
        val (xs, ys, xIndexes, yIndexes) = compressCoordinates(points)

        val inside = rasterize(points, xs, ys, xIndexes, yIndexes)
        val pref = prefixSum(inside)

        var maxArea = 0L

        for ((i, corner1) in points.withIndex()) {
            val (corner1XIndex, corner1YIndex) = xIndexes[corner1.x]!! to yIndexes[corner1.y]!!
            for (j in i + 1 until points.size) {
                val corner2 = points[j]
                val (corner2XIndex, corner2YIndex) = xIndexes[corner2.x]!! to yIndexes[corner2.y]!!

                val (xMin, xMax) = if(corner1XIndex < corner2XIndex) corner1XIndex to corner2XIndex else corner2XIndex to corner1XIndex
                val (yMin, yMax) = if(corner1YIndex < corner2YIndex) corner1YIndex to corner2YIndex else corner2YIndex to corner1YIndex

                val totalCells = (xMax - xMin) * (yMax - yMin)

                val filledCells = sumCells(pref, xMin, xMax, yMin, yMax)
                if (filledCells == totalCells) {
                    val area = (xs[xMax] - xs[xMin] + 1L) * (ys[yMax] - ys[yMin] + 1)

                    maxArea = maxOf(maxArea, area)
                }
            }
        }
        return maxArea
    }

}


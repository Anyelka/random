package com.random.problems.adventOfCode.twentyFive.day9

import com.random.util.getResourceAsText
import kotlin.math.abs
import kotlin.time.measureTime


const val FILE_PATH = "/adventOfCode/2025/day9/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 7 - Part 1 solution: ${Part1.solve(parse(lines.filter { it.isNotEmpty() } ))}")
    }
    println("       -> Time taken: $timeTaken")
}

fun parse(lines: List<String>): List<Pair<Int,Int>> {
    return lines.map{ it.split(",")}.map { it[0].toInt() to it[1].toInt() }
}

object Part1 {
    fun solve(points: List<Pair<Int,Int>>): Long {
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

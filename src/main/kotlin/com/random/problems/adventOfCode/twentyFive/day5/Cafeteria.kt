package com.random.problems.adventOfCode.twentyFive.day5

import com.random.util.getResourceAsText
import kotlin.time.measureTime


const val FILE_PATH = "/adventOfCode/2025/day5/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 5 - Part 1 solution: ${Part1.solve(parse(lines))}")
        println("AOC day 5 - Part 2 solution: ${Part2.solve(parse(lines))}")
    }
    println("       -> Time taken: $timeTaken")
}

fun parse(lines: List<String>): Pair<List<LongArray>, List<Long>> {
    val intervals = mutableListOf<LongArray>()
    val ids = mutableListOf<Long>()
    var isInterval = true
    for(line in lines) {
        if(line.isEmpty()) {
            isInterval = false
        } else {
            if(isInterval) {
                intervals.add(line.split("-").map { it.toLong() }.toLongArray())
            } else {
                ids.add(line.toLong())
            }
        }
    }

    return intervals to ids
}

object Part1 {
    fun solve(input:  Pair<List<LongArray>, List<Long>>): Int {
        val intervals = input.first.sortedBy { it[0] }
        val ids = input.second.sortedBy { it }

        var result = 0
        var i = 0
        for(id in ids) {
            while(i < intervals.size && intervals[i][1] < id) {
                i++
            }
            if(i < intervals.size && intervals[i][0] < id) result++
        }

        return result
    }
}

object Part2 {
    fun solve(input:  Pair<List<LongArray>, List<Long>>): Long {
        val intervals = input.first.sortedWith(compareBy<LongArray> { it[0] }.thenBy { -it[1] })

        val mergedIntervals = intervals.merge()

        return mergedIntervals.sumOf { it[1] - it[0] + 1 }
    }
}

private fun List<LongArray>.merge(): List<LongArray> {
    val result = mutableListOf<LongArray>()

    var min = this[0][0]
    var max = this[0][1]

    for(i in 1..this.lastIndex) {
        if(this[i][0] > max) {
            result.add(longArrayOf(min, max))
            min = this[i][0]
            max = this[i][1]
        } else {
            max = maxOf(max, this[i][1])
        }
    }
    result.add(longArrayOf(min, max))
    return result
}


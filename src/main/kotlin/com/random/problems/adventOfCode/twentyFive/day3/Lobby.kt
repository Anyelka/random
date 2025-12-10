package com.random.problems.adventOfCode.twentyFive.day3

import com.random.util.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2025/day3/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 3 - Part 1 solution: ${Part1.solve(parse(lines))}")
        println("AOC day 3 - Part 2 solution: ${Part2.solve(parse(lines))}")
    }
    println("       -> Time taken: $timeTaken")
}

fun parse(lines: List<String>): List<IntArray> {
    return lines.map { it.toCharArray().map { a -> a.digitToInt() }.toIntArray() }
}

object Part1 {
    fun solve(batteryBanks: List<IntArray>): Int {
        return batteryBanks.sumOf { it.maxJoltage() }
    }

    private fun IntArray.maxJoltage(): Int {
        var first = this[0]
        var second = 0
        for(i in 1..this.lastIndex) {
            if(this[i] > first && i < this.lastIndex) {
                first = this[i]
                second = 0
            } else if(this[i] > second) {
                second = this[i]
            }
        }
        return 10 * first + second
    }
}

object Part2 {
    val bankSize = 12

    fun solve(batteryBanks: List<IntArray>): Long {
        return batteryBanks.sumOf { it.maxJoltage() }
    }

    private fun IntArray.maxJoltage(): Long {
        var result = 0L
        var minI = 0
        var numbersSelected = 0
        for(n in 1..bankSize) {
            val maxI = this.size - (bankSize - numbersSelected)
            var max = Int.MIN_VALUE
            for(i in minI..maxI) {
                if(this[i] > max) {
                    max = this[i]
                    minI = i + 1
                }
            }
            result *= 10
            result += max
            numbersSelected++
        }
        return result
    }

    /*
    * 425
    * 012
    *
    * banksize = 2
    * max = 1 = this.size - (bankSize - numbersSelected) = 2 - (2 - 0)
    *
    * */
}
package com.random.problems.adventOfCode.twentyFive.day2

import com.random.util.getResourceAsText
import com.random.util.pow
import com.random.util.toDigits
import kotlin.math.pow
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2025/day2/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 2 - Part 1 solution: ${Part1.solve(parse(lines[0]))}")
        println("AOC day 2 - Part 2 solution: ${Part2.solve(parse(lines[0]))}")
    }
    println("       -> Time taken: $timeTaken")
}

fun parse(line: String): Array<LongArray> {
    return line.split(",").map { it.split("-").map { it.toLong() }.toLongArray() }.toTypedArray()
}

object Part1 {
    fun solve(intervals: Array<LongArray>): Long {
        var result = 0L
        for(interval in intervals) {
            var num = interval[0] - 1
            while(num <= interval[1]) {
                num = num.findNextInvalid()
                if(num <= interval[1]) {
                    result += num
                }
            }
        }
        return result
    }

    fun Long.findNextInvalid(): Long {
        val digits = this.toDigits().size
        val half = if(digits % 2 == 0) {
            val currHalf = (this / (10.0.pow(digits / 2.0)).toLong())
            if(currHalf.doubleDigits() > this) {
                currHalf
            } else {
                currHalf + 1
            }
        } else {
            10.0.pow(digits / 2).toLong()
        }
        return half.doubleDigits()
    }

    fun Long.doubleDigits(): Long {
        return this + this * 10.pow(this.toDigits().size)
    }
}

object Part2 {
    fun solve(intervals: Array<LongArray>): Long {
        var result = 0L
        for((start, end) in intervals) {
            for(i in start..end) {
                if(i.isInvalid()) {
                    result += i
                }
            }
        }
        return result
    }

    fun Long.isInvalid(): Boolean {
        val digits = this.toDigits()
        if(digits.size < 2) return false
        // 1. iterate over all lengths of sequences
        for(i in 1..digits.size / 2) {
            var isInvalid = true
            val tens = 10.pow(i)
            val sequence = this % tens
            if(sequence == 0L) continue

            // 2. iterate over positions
            var num = this
            while(num > 0) {
                if((num - sequence) % tens != 0L) {
                    isInvalid = false
                    break
                }
                num /= tens
            }

            if(isInvalid && (digits.size % i) == 0) {
                return true
            }

        }
        return false
    }

}
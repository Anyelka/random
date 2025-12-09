package com.random.problems.adventOfCode.twentyFive.day1

import com.random.problems.other.dragonByte.round1.binary.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2025/day1/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 1 - Part 1 solution: ${Part1.getZeroCount(lines)}")
        println("AOC day 1 - Part 2 solution: ${Part2.getZeroCount(lines)}")
    }
    println("       -> Time taken: $timeTaken")
}

const val STARTING_POINT = 50

object Part1 {
    fun getZeroCount(lines: List<String>): Int {
        var i = STARTING_POINT
        var result = 0
        for(line in lines) {
            val direction = line[0]
            val value = line.substring(1).toInt()
            val diff = if(direction == 'L') - value else value
            i = (i + diff) % 100
            if(i == 0) result++
        }
        return result
    }
}

object Part2 {
    fun getZeroCount(lines: List<String>): Int {
        var i = STARTING_POINT
        var result = 0
        for(line in lines) {
            val operation: (Int,Int) -> Int = if(line[0]  == 'L') Int::minus else Int::plus
            val revOp: (Int,Int) -> Int = if(line[0]  == 'R') Int::minus else Int::plus
            var value = operation(0,line.substring(1).toInt())
            while (value != 0) {
                i = operation(i,1)
                value = revOp(value, 1)
                i %= 100
                if(i == 0) result++
            }
        }
        return result
    }
}
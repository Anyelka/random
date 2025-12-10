package com.random.problems.adventOfCode.twentyFive.day6

import com.random.util.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2025/day6/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 6 - Part 1 solution: ${Part1.solve(parse(lines.filter { it.isNotEmpty() }))}")
    }
    println("       -> Time taken: $timeTaken")
}

fun parse(lines: List<String>): List<Pair<List<Long>, Char>> {
    val numbers = mutableListOf<MutableList<Long>>()
    val operators = mutableListOf<Char>()
    lines.withIndex().forEach { (i, line) ->
        line.split(" ").filter{ it.isNotEmpty() }.withIndex().forEach { (j, entry) ->
            if(i == lines.lastIndex) operators.add(entry[0])
            else {
                if(j <= numbers.size) {
                    numbers.add(mutableListOf())
                }
                if(i <= numbers[j].size) {
                    numbers[j].add(entry.trim().toLong())
                }
            }
        }
    }

    return numbers.filter{ it.isNotEmpty() }.withIndex().map { (i, nums) -> nums to operators[i] }
}

object Part1 {
    fun solve(sequences: List<Pair<List<Long>, Char>>): Long {
        return sequences.sumOf { it.sumUp() }
    }

    private fun Pair<List<Long>, Char>.sumUp(): Long {
        val operator: (Long, Long) -> Long = when(second) {
            '+' -> Long::plus
            '*' -> Long::times
            else -> throw IllegalArgumentException()
        }
        return first.reduce(operator)
    }
}

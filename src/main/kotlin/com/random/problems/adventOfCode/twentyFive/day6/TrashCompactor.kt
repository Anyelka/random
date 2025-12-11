package com.random.problems.adventOfCode.twentyFive.day6

import com.random.util.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2025/day6/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 6 - Part 1 solution: ${Part1.solve(lines.filter { it.isNotEmpty() })}")
        println("AOC day 6 - Part 2 solution: ${Part2.solve(lines.filter { it.isNotEmpty() }.map { it.toCharArray() })}")
    }
    println("       -> Time taken: $timeTaken")
}

object Part1 {
    fun solve(lines: List<String>): Long {
        return parse(lines).sumOf { it.sumUp() }
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

    private fun Pair<List<Long>, Char>.sumUp(): Long {
        val operator: (Long, Long) -> Long = when(second) {
            '+' -> Long::plus
            '*' -> Long::times
            else -> throw IllegalArgumentException()
        }
        return first.reduce(operator)
    }
}

object Part2 {
    fun solve(lines: List<CharArray>): Long {
        val numGroups = mutableListOf<List<Long>>()
        val operations = mutableListOf<(Long, Long) -> Long>()

        val maxCol = lines.maxOf { it.size } - 1

        var currentNumGroup = mutableListOf<Long>()
        for(j in 0..maxCol) {
            var num = 0L
            for(i in lines.indices) {
                if(j > lines[i].lastIndex || lines[i][j] == ' ') continue
                if(lines[i][j] == '*') {
                    operations.add(Long::times)
                    continue
                }
                if(lines[i][j] == '+') {
                    operations.add(Long::plus)
                    continue
                }
                num *= 10
                num += lines[i][j].digitToInt()
            }

            if(num == 0L) {
                numGroups.add(currentNumGroup)
                currentNumGroup = mutableListOf()
            } else {
                currentNumGroup.add(num)
            }
        }
        numGroups.add(currentNumGroup)

        return numGroups.withIndex().sumOf { (i, group) -> group.reduce(operations[i]) }
    }
}


package com.random.problems.adventOfCode.twentyFive.day4


import com.random.util.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2025/day4/TestCase1"

const val PAPER_ROLL = '@'

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 4 - Part 1 solution: ${Part1.solve(parse(lines))}")
        println("AOC day 4 - Part 2 solution: ${Part2.solve(parse(lines))}")
    }
    println("       -> Time taken: $timeTaken")
}

fun parse(lines: List<String>): Array<Array<Char>> {
    return lines.map { it.toCharArray().toTypedArray() }.filter { it.isNotEmpty() }.toTypedArray()
}

object Part1 {
    fun solve(grid: Array<Array<Char>>): Int {
        return grid.withIndex().sumOf { (i, row) -> row.withIndex().count { (j, char) -> isAccessible(grid, i, j) } }
    }

    fun isAccessible(grid: Array<Array<Char>>, i: Int, j: Int): Boolean {
        if(grid[i][j] != PAPER_ROLL) return false
        var blockedSides = 0
        for(x in i-1..i+1) {
            if(x !in 0..grid.lastIndex) continue
            for(y in j-1..j+1) {
                if(y !in 0..grid[0].lastIndex) continue
                if(x == i && y == j) continue
                if(grid[x][y] == PAPER_ROLL) {
                    blockedSides++
                }

                if(blockedSides >= 4) return false
            }
        }
        return true
    }
}

object Part2 {
    fun solve(grid: Array<Array<Char>>): Int {
        var result = 0
        var removed = 0
        do {
            removed = 0
            for(i in grid.indices) {
                for(j in grid[0].indices) {
                    if(isAccessible(grid, i, j)) {
                        grid[i][j] = '.'
                        removed++
                    }
                }
            }
            result += removed
        } while(removed != 0)

        return result
    }

    fun isAccessible(grid: Array<Array<Char>>, i: Int, j: Int): Boolean {
        if(grid[i][j] != PAPER_ROLL) return false
        var blockedSides = 0
        for(x in i-1..i+1) {
            if(x !in 0..grid.lastIndex) continue
            for(y in j-1..j+1) {
                if(y !in 0..grid[0].lastIndex) continue
                if(x == i && y == j) continue
                if(grid[x][y] == PAPER_ROLL) {
                    blockedSides++
                }

                if(blockedSides >= 4) return false
            }
        }
        return true
    }


}

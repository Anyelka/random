package com.random.problems.adventOfCode.twentyFive.day7

import com.random.util.getResourceAsText
import kotlin.time.measureTime

const val FILE_PATH = "/adventOfCode/2025/day7/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val timeTaken = measureTime {
        println("AOC day 7 - Part 1 solution: ${Part1.solve(lines.filter { it.isNotEmpty() })}")
        println("AOC day 7 - Part 2 solution: ${Part2.solve(lines.filter { it.isNotEmpty() })}")
    }
    println("       -> Time taken: $timeTaken")
}

object Part1 {
    fun solve(lines: List<String>): Int {
        val maxCol = lines[0].lastIndex
        val queue = ArrayDeque<Pair<Int,Int>>()
        queue.add(0 to lines[0].indices.find { lines[0][it] == 'S' }!!)
        val visited = mutableSetOf<Pair<Int,Int>>()

        var count = 0
        while(queue.isNotEmpty()) {
            val (i, j) = queue.removeFirst()
            if(i >= lines.lastIndex) continue
            if(visited.contains(i to j)) continue
            visited.add(i to j)
            if(lines[i + 1][j] == '^') {
                count++
                (i to j + 1).takeIf { it.second <= maxCol }?.let { queue.addIfNotPresent(it) }
                (i to j - 1).takeIf { it.second >= 0 }?.let { queue.addIfNotPresent(it) }
            } else {
                queue.addIfNotPresent(i + 1 to  j)
            }
        }

        return count
    }
}

private fun ArrayDeque<Pair<Int, Int>>.addIfNotPresent(cell: Pair<Int, Int>)  {
    if(!this.contains(cell)) this.add(cell)
}

object Part2 {
    fun solve(lines: List<String>): Long {
        val maxCol = lines[0].lastIndex
        val memo = Array(lines.size) { Array<Long?>(maxCol + 1) { null } }
        fun dfs(i: Int, j: Int): Long {
            if(memo[i][j] != null) return memo[i][j]!!
            val result =  if(i >= lines.lastIndex) {
                1
            } else if(lines[i][j] == '^') {
                dfs(i + 1, j + 1) + dfs(i + 1, j - 1)
            } else {
                dfs(i + 1, j)
            }
            memo[i][j] = result
            return result
        }

        return dfs(0, lines[0].indices.find { lines[0][it] == 'S' }!! )
    }
}
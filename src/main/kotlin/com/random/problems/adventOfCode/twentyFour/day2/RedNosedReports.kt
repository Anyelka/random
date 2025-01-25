package com.random.problems.adventOfCode.twentyFour.day2

import com.random.util.getResourceAsText

const val FILE_PATH = "/adventOfCode/2024/day2/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH)!!.lines()

    val safeReportCount = Part2.countSafeReports(lines)

    println("Number of safe reports: $safeReportCount")
}

object Part1: Part() {
     override fun isSafe(levels: MutableList<Int>): Boolean {
        return isSafeReport(levels)
    }
}

object Part2: Part() {
    override fun isSafe(levels: MutableList<Int>): Boolean {
        return isSafeReport(levels) || levels.indices.any { index -> levels.isSafeWithRemovalOf(index) }
    }

    private fun MutableList<Int>.isSafeWithRemovalOf(levelIndex: Int): Boolean {
        val levelsWithOneRemoved = this.toMutableList()
        levelsWithOneRemoved.removeAt(levelIndex)
        return isSafeReport(levelsWithOneRemoved)
    }
}


abstract class Part {
    fun countSafeReports(lines: List<String>): Int {
        return lines.map(::isSafeAsInt).sumOf { it }
    }

    abstract fun isSafe(levels: MutableList<Int>): Boolean

    protected fun isSafeReport(levels: List<Int>): Boolean {
        if (levels[0] == levels[1]) {
            return false
        }

        val isAscending = levels[0] < levels[1]

        for (i in 0..<levels.size - 1) {
            if (!isSafeLevelDifference(isAscending, levels[i], levels[i + 1])) {
                return false
            }
        }

        return true
    }

    private fun isSafeAsInt(line: String): Int = if(isSafe(line)) 1 else 0

    private fun isSafe(line: String): Boolean = isSafe(getLevels(line))

    private fun getLevels(line: String) = line.split(" ").map { it.toInt() }.toMutableList()


    private fun isSafeLevelDifference(isAscending: Boolean, current: Int, next: Int): Boolean {
        val diff = if(isAscending) next - current else current - next
        return diff in 1..3
    }
}

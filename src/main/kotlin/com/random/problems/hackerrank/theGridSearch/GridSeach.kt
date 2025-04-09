package com.random.problems.hackerrank.theGridSearch

import com.random.util.getResourceAsText
import com.random.util.test
import com.random.util.testWithoutPrintInput

const val INPUT_SOURCE = "/hackerrank/theGridSearch/TestCase"

fun main() {
    val testCases = listOf(/*"00", "01", */"5")

    val manualInput = listOf(
        listOf((arrayOf("12", "56", "90") to arrayOf("12", "56", "90")) to "YES"),
        listOf((arrayOf("1", "5") to arrayOf("1",)) to "YES"),
        listOf((arrayOf("12", "56", "90") to arrayOf("56", "90")) to "YES"),
        listOf((arrayOf("12", "56", "90") to arrayOf("1", "5", "9")) to "YES"),
        listOf((arrayOf("12", "56", "90") to arrayOf("5", "9")) to "YES"),
        listOf((arrayOf("12", "56", "90") to arrayOf("5")) to "YES"),

        listOf((arrayOf("01", "56") to arrayOf("1")) to "YES"),

        listOf((arrayOf("010984567", "56") to arrayOf("1")) to "YES")
    )
    val input =
                testCases.map { toInput(it) } // + manualInput

    input.forEach { it.test() }
}

fun toInput(testCase: String): List<Pair<Pair<Array<String>, Array<String>>, String>> {
    val input = getResourceAsText(INPUT_SOURCE + testCase + "_in")!!.lines()
    val output = getResourceAsText(INPUT_SOURCE + testCase + "_out")!!.lines()

    val games = convertInput(input).withIndex().map { (i, it) -> it to output[i] }

    return games
}

private fun List<Pair<Pair<Array<String>, Array<String>>, String>>.test() {
    this.forEach {
        val (grid, pattern) = it.first
        it.testWithoutPrintInput { Solution1().gridSearch(grid, pattern) }
    }
}

fun convertInput(input: List<String>): List<Pair<Array<String>, Array<String>>> {
    val lines = input.drop(1)
    val grids = mutableListOf<Array<String>>()
    var isGrid = false
    val patterns = mutableListOf<Array<String>>()
    var currentGrid = mutableListOf<String>()
    var currentPattern = mutableListOf<String>()
    for(line in lines) {
        val split = line.split(" ")
        if(split.size > 1) {
            if(isGrid) {
                grids.add(currentGrid.toTypedArray())
            } else if(currentPattern.size > 0){
                patterns.add(currentPattern.toTypedArray())
            }
            isGrid = !isGrid
        } else {
            if(isGrid) {
                currentGrid.add(line)
            } else {
                currentPattern.add(line)
            }
        }
    }
    if(currentPattern.isNotEmpty()) patterns.add(currentPattern.toTypedArray())

    return grids.withIndex().map { (i, grid) -> grid to patterns[i] }
}

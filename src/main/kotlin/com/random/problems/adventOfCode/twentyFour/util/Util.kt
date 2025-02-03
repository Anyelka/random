package com.random.problems.adventOfCode.twentyFour.util


import java.nio.file.Files
import java.nio.file.Paths
import kotlin.time.measureTime

fun runAndLogTime(runnable: Runnable) {
    val timeTaken = measureTime {
        runnable.run()
    }
    println("Time taken: $timeTaken")
}

fun writeToFile(filename: String, map: Grid) = Files.writeString(Paths.get(filename), getFormattedMap(map))

private fun getFormattedMap(map: Grid) = map.formatted()

fun Grid.formatted(): String {
    val fields: Array<Array<String>> = this.fields.map { it.map { i -> i.toString() }.toTypedArray() }.toTypedArray()
    val allLines = lineWithColumnIndexes(fields[0].size) + emptyLine(fields.size) + fields
    return allLines.withIndex().joinToString("\n") { (i, it) -> formatWithRowIndex(i, it) }
}

private fun lineWithColumnIndexes(size: Int) =
    arrayOf((0..<size).map { it.toString() }.toTypedArray<String>())
    //arrayOf(fields[0].indices.map { it.toString() }.toTypedArray<String>())

private fun emptyLine(size: Int) = Array<String>(size) { " " }

private fun formatWithRowIndex(i: Int, it: Array<String>) = "${getRowIndex(i)}    " + it.joinToString("  ")

private fun getRowIndex(i: Int): String = if(i > 1) (i - 2).toString() else " "

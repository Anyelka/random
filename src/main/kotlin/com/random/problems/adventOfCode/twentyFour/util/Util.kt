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

private fun getFormattedMap(map: Grid): String {
    val fields: Array<Array<String>> = map.fields.map { it.map { i -> i.toString() }.toTypedArray() }.toTypedArray()
    val newFields = arrayOf(fields[0].indices.map { it.toString() }.toTypedArray<String>()) + fields
    return newFields.withIndex().joinToString("\n") { (i, it) -> "${getColumnRowNumberValue(i)}    " + it.joinToString("  ") }
}

private fun getColumnRowNumberValue(i: Int): Int = if(i > 0) i - 1 else i
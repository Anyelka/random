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

private fun getFormattedMap(map: Grid) = map.getFormattedMap()
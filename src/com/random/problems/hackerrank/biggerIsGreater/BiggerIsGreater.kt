package com.random.problems.hackerrank.biggerIsGreater

import com.random.util.getResourceAsText

const val FILE_PATH = "/hackerrank/biggerIsGreater/TestCase01"

fun main() {
    val lines = getResourceAsText(FILE_PATH + "_in")!!.lines()
    val inputLines = lines.takeLast(lines.size-1)
    val outputLines = getResourceAsText(FILE_PATH + "_out")!!.lines()

    for((index, line) in inputLines.withIndex()) {
        val result = getNextGreater(line)
        val outputLine = outputLines[index]
        if(outputLine != result) {
            System.err.println("$index. line: $outputLine has incorrect result: $result")
        }
    }
}

fun getNextGreater(line: String): String {
    val charArray = line.toCharArray()
    //TODO
    return ""
}

fun String.getValue(): Long {
    var value: Double = 0.0
    val charArray = toCharArray()
    for ((index, char) in charArray.withIndex()) {
            value += char.getValue() * Math.pow(100.0, (charArray.size - index).toDouble())
    }
    return value.toLong()
}

fun Char.getValue(): Int {
    return charValueMap[this + ""]!!
}

val charValueMap = mapOf(
        "a" to 1,
        "b" to 2,
        "c" to 3,
        "d" to 4,
        "e" to 5,
        "f" to 6,
        "g" to 7,
        "h" to 8,
        "i" to 9,
        "j" to 10,
        "k" to 11,
        "l" to 12,
        "m" to 13,
        "n" to 14,
        "o" to 15,
        "p" to 16,
        "q" to 17,
        "r" to 18,
        "s" to 19,
        "t" to 20,
        "u" to 21,
        "v" to 22,
        "w" to 23,
        "x" to 24,
        "y" to 25,
        "z" to 26
)

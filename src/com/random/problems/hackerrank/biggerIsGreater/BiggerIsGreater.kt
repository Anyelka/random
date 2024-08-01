package com.random.problems.hackerrank.biggerIsGreater

import com.random.util.getResourceAsText
import java.util.*
import kotlin.math.pow
import kotlin.time.measureTime

const val FILE_PATH = "/hackerrank/biggerIsGreater/TestCase1"

fun main() {
    val lines = getResourceAsText(FILE_PATH + "_in")!!.lines()
    val inputLines = lines.takeLast(lines.size-1)
    val outputLines = getResourceAsText(FILE_PATH + "_out")!!.lines()

    val runtime = measureTime {
        var hasErrors = false
        for((index, line) in inputLines.withIndex()) {
            val result = getNextGreater(line)
            println(result)
            val outputLine = outputLines[index]
            if(outputLine != result) {
                hasErrors = true
                System.err.println("$index. line is expected to be: '$outputLine', but was: '$result'")
            }
        }
        if(!hasErrors) {
            println("   =======  PERFECT SOLUTION!   =======  ")
        }
    }
    println("   =======  Time taken to run: $runtime")
}

fun getNextGreater(word: String): String {
    var greaterWords = mutableListOf<String>()
    getGreaterWords(word.toCharArray().map{it.toString()}, 0, greaterWords, word)
    greaterWords.sortBy { it.getValue() }
    return if(greaterWords.size > 0) greaterWords[0] else "no answer"
}

fun getGreaterWords(word: List<String>, index: Int, greaterWords: MutableList<String>, originalLine: String) {
    if(index == word.size) {
        val newWord = word.joinToString("")
        if(newWord.getValue() > originalLine.getValue()) {
            greaterWords.add(word.joinToString(""))
        }
    }
    for(i: Int in index..word.lastIndex) {
        Collections.swap(word, index, i)
        getGreaterWords(word, index + 1, greaterWords, originalLine)
        Collections.swap(word, i, index)
    }
}

fun String.getValue(): Long {
    var value = 0.0
    val charArray = toCharArray()
    for ((index, char) in charArray.withIndex()) {
            value += char.getCharValue() * 100.0.pow((charArray.size - index).toDouble())
    }
    return value.toLong()
}

fun Char.getCharValue(): Int {
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

package com.random.problems.hackerrank.biggerIsGreater

import com.random.util.getResourceAsText
import com.random.util.swap
import java.math.BigInteger

const val FILE_PATH = "/hackerrank/biggerIsGreater/TestCase"

fun main() {
    val testCases = intArrayOf(0, 1, 4)
    testCases.forEach { it.test() }
}

private fun Int.test() {
    println("TestCase$this:")
    val inputLines = getResourceAsText("${FILE_PATH}${this}_in")!!.lines().drop(1)
    val outputLines = getResourceAsText("${FILE_PATH}${this}_out")!!.lines()

    val inputData = inputLines.withIndex().map { (i, line) -> line to outputLines[i] }
    // val inputData = listOf("fedcbabcd" to "fedcbabdc")
    inputData.forEach { (word, expectedResult) ->
        val result = biggerIsGreater(word)
        println("Result for $word: $result - ${correct(result, expectedResult)}")
    }
}

fun correct(result: String, expected: String): String = if (result == expected) "Correct" else "WRONG SOLUTION !!!"

fun biggerIsGreater(w: String): String {
    return biggerIsGreaterNextPermutation(w)
}

// Brute Force Solution:
//      generate all possible words from the letters
//      calculate the lexographical value for each of the words
//      sort the words by lexographical value
//      get the next word after the initial word
//  Time complexity: O(n!*log(n!))
//  Space complexity: O(n*n!)
private fun biggerIsGreaterBruteForce(word: String): String {
    val charMap = getCharMap(word)
    val allPossibleWords = generateAllPossiblePermutations(charMap)
    val sortedWords = withLexicographicalValues(allPossibleWords).map { it.first }
    val currentWordIndex = sortedWords.indexOf(word)
    return if (currentWordIndex != -1 && currentWordIndex != sortedWords.size - 1) sortedWords[currentWordIndex + 1] else "no answer"
}

private fun withLexicographicalValues(allPossibleWords: List<String>) =
    allPossibleWords.map { it to it.lexicographicValue() }.sortedBy { it.second }

private fun getCharMap(word: String): MutableMap<Char, Int> {
    val charMap = mutableMapOf<Char, Int>()
    word.toCharArray().forEach { charMap[it] = charMap.getOrDefault(it, 0) + 1 }
    return charMap
}

private fun String.lexicographicValue(): BigInteger = toCharArray().withIndex()
    .sumOf { (i, char) -> 100.toBigInteger().pow(this.length - 1 - i) * char.lexicographicValue() }

private fun Char.lexicographicValue(): BigInteger = (code.toBigInteger() - 'a'.code.toBigInteger() + BigInteger.ONE)

fun generateAllPossiblePermutations(charMap: MutableMap<Char, Int>): List<String> {
    val allPossiblePermutations = mutableListOf<String>()

    if (charMap.values.sum() == 0) return allPossiblePermutations.also { it.add("") }

    for ((char, frequency) in charMap) {
        if (frequency == 0) continue
        val nextCharMap = charMap.toMutableMap().also { it[char] = it[char]!! - 1 }
        generateAllPossiblePermutations(nextCharMap).forEach { allPossiblePermutations.add(char + it) }
    }
    return allPossiblePermutations
}

// Next Permutation Solution:
//      - find the first char from the right (lowest place value = i) that is greater than the next one
//          - this will be the first one that can be rearranged
//          - if no such char, the string is lexicographically ordered (descending order) - no Greater string
//      - find the first char from the right (j) that is greater than the previous element
//      - swap the two chars: word[i] <-> word[j]
//      - reverse the substring from the swapped element (i+1) so that the part after the swapped element will have
///             the lowest lexicographical value
//    Time complexity: O(n)
//    Space complexity: O(1)
fun biggerIsGreaterNextPermutation(word: String): String {
    val charArray = word.toCharArray()
    val n = charArray.size

    var i = n - 2
    while (i >= 0 && charArray[i] >= charArray[i + 1]) i--

    if (i == -1) return "no answer"

    var j = n - 1
    while (charArray[j] <= charArray[i]) j--

    charArray.swap(i, j)

    charArray.reverse(i + 1, n)

    return String(charArray)
}

package com.random.problems.other.synonyms

import com.random.util.getResourceAsText

const val FILE_PATH = "/other/synonyms/example_big"

fun main() {
    val lines = getResourceAsText("$FILE_PATH.in")!!.lines()

    var readingDictionary = false
    var currentDictionary = HashMap<String, MutableSet<String>>()
    var results = ArrayList<String>()
    for((index, line) in lines.withIndex()) {
        if(index == 0 || line.isEmpty()) {
            continue
        }
        val intLine = line.toIntOrNull()
        if(intLine != null) {
            if(!readingDictionary) {
                currentDictionary = HashMap<String, MutableSet<String>>()
            }
            readingDictionary = !readingDictionary
        } else {
            val words = line.split(" ")
            val word1 = words[0].lowercase()
            val word2 = words[1].lowercase()
            if(readingDictionary) {
                currentDictionary.addWords(word1, word2)
            } else {
                results.add(areSynonyms(word1, word2, currentDictionary))
            }
        }
    }
    // 1. simply print the result:
    /*println(results.joinToString("\n"))*/

    // 2. compare the result with the expected .out file
    val outputLines = getResourceAsText("$FILE_PATH.out")!!.lines()
    var hasError = false
    for((index, line) in results.withIndex()) {
        val expectedResult = outputLines[index]
        if(line != expectedResult) {
            System.err.println("$index. result is wrong: it should be $expectedResult but it is: $line")
            hasError = true
        }
    }
    if(!hasError) {
        println("PERFECT SOLUTION!")
    }

}

fun areSynonyms(word1: String, word2: String, dictionary: HashMap<String, MutableSet<String>>): String {
    var visitedWords = ArrayList<String>()
    return if(areSynonyms(word1, word2, dictionary, visitedWords)) "synonyms" else "different"
}

fun areSynonyms(word: String, otherWord: String, dictionary: HashMap<String, MutableSet<String>>, visitedWords: ArrayList<String>): Boolean {
    if(visitedWords.contains(word)) {
        return false
    }
    visitedWords.add(word)
    return word == otherWord || dictionary[word]?.any { areSynonyms(it, otherWord, dictionary, visitedWords) } ?: false
}

fun HashMap<String, MutableSet<String>>.addWords(word1: String, word2: String) {
    addWord(word1, word2)
    addWord(word2, word1)
}

fun HashMap<String, MutableSet<String>>.addWord(word: String, synonym: String) {
    this.computeIfAbsent(word) { mutableSetOf() }.add(synonym)
}
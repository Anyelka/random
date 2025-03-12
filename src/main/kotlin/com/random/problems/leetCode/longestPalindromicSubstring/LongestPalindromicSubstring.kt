package com.random.problems.leetCode.longestPalindromicSubstring

import kotlin.math.max

fun main() {
    getInput().forEach { it.test()}
}

private fun Pair<String, Array<String>>.test() {
    val result = longestPalindrome(first)
    println("Result for ${first}: $result - ${correct(result, second)}")
}

fun correct(result: String, solutions: Array<String>) = if(solutions.contains(result)) "Correct" else "WRONG RESULT !!!"

fun getInput(): List<Pair<String, Array<String>>> {
    return listOf(
        "babad" to arrayOf("bab", "aba"),
        "cbbd" to arrayOf("bb"),
        "bb" to arrayOf("bb")
    )
}

fun longestPalindrome(s: String): String {
    return longestPalindromeExpandAroundCenter(s)
}

// Brute Force Solution:
//          get all the substrings and check for palindromes
//          keep track of the longest one
//      Time complexity: O(n^3)
//      Space complexity: O(n^2)
fun longestPalindromeBruteForce(s: String): String {
    val charArray = s.toCharArray()
    var longestPalindrome = charArray[0].toString()
    for(i in charArray.indices) {
        for(j in i..charArray.size) {
            val substring = s.substring(i, j)
            if(substring.isPalindrome() && substring.length > longestPalindrome.length) {
                longestPalindrome = substring
            }
        }
    }
    return longestPalindrome
}

private fun String.isPalindrome(): Boolean {
    val charArray = toCharArray()
    for(i in 0..< charArray.size / 2) {
        if(charArray[i] != charArray[charArray.size - 1 - i]) {
            return false
        }
    }
    return true
}

//  Expand Around Center Solution:
//          go through each character and expand from them to both sides until we find different letters on each side
//              do it for odd and even number of letters
//          take the bigger size from the two and store the start & end if the substring is longer than the stored length
//      Time complexity: O(n^2)
//      Space complexity: O(1)


fun longestPalindromeExpandAroundCenter(word: String): String {
    var start = 0
    var end = 0
    for(i in word.indices) {
        val maxLengthWithOddChars = maxPalindromeLengthAround(word, i, i)
        val maxLengthWithEvenChars = maxPalindromeLengthAround(word, i, i + 1)
        val maxLength = max(maxLengthWithOddChars, maxLengthWithEvenChars)

        if(maxLength > end - start) {
            start = i - (maxLength - 1) / 2
            end = i + maxLength / 2
        }
    }

    return word.substring(start, end + 1)
}

private fun maxPalindromeLengthAround(word: String, left: Int, right: Int): Int {
    var l = left
    var r = right
    while (l >= 0 && r < word.length && word[l] == word[r]) {
        l--
        r++
    }
    return r - l - 1
}
package com.random.problems.leetCode.longestPalindromicSubstring

fun main() {
    listOf(
        "babad" to arrayOf("bab", "aba"),
        "cbbd" to arrayOf("bb"),
        "bb" to arrayOf("bb"),
        "cb" to arrayOf("c", "b"),
        "acbde" to arrayOf("a","c", "b","d","e")
    ).forEach { it.test() }
}

private fun Pair<String, Array<String>>.test() {
    val result = Solution3().longestPalindrome(first)
    println("Result for ${first}: $result - ${correct(result, second)}")
}

fun correct(result: String, solutions: Array<String>) = if(solutions.contains(result)) "Correct" else "WRONG RESULT !!!"


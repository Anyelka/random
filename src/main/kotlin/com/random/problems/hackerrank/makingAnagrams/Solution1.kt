package com.random.problems.hackerrank.makingAnagrams

class Solution1 {
    fun makingAnagrams(s1: String, s2: String): Int {
        return makingAnagrams1(s1, s2)
    }

    // 1. Solution
    //      get a char difference map between the two strings:
    //      - iterate over chars in first string and add them into char frequency map = increment their frequency
    //      - iterate over chars in second string and delete them from char frequency map = decrement their frequency
    //      - the solution is the total sum of the absolute value of values in the map
    //
    //      Time complexity:    O(n+m)
    //      Space complexity:   O(1)
    //          - because we store values for each of the characters, and the max number of chars is 26, so effectively
    //          O(k) = O(1), where max(k) = 26
    //
    private fun makingAnagrams1(s1: String, s2: String): Int {
        val charMap = mutableMapOf<Char, Int>()
        s1.forEach { charMap[it] = charMap.getOrDefault(it, 0) + 1 }
        s2.forEach { charMap[it] = charMap.getOrDefault(it, 0) - 1 }
        return charMap.values.sumOf { it.absoluteValue() }
    }

    private fun Int.absoluteValue() = if(this < 0) -this else this
}
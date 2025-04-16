package com.random.problems.hackerrank.makingAnagrams

import com.random.util.absoluteValue

class Solution2 {
    fun makingAnagrams(s1: String, s2: String): Int {
        val charOccurences = mutableMapOf<Char,Int>()
        for(c in s1.toCharArray()) {
            charOccurences[c] = (charOccurences[c] ?: 0) + 1
        }
        for(c in s2.toCharArray()) {
            charOccurences[c] = (charOccurences[c] ?: 0) - 1
            if(charOccurences[c] == 0) charOccurences.remove(c)
        }

        return charOccurences.values.sumOf { it.absoluteValue() }
    }
}
package com.random.problems.leetCode.longestSubstring



fun main() {
    val strings = arrayOf("abcabcbb", "bbbbb", "pwwkew", " ")
    strings.forEach {
        val result = LongestSubstring().lengthOfLongestSubstring(it)
        println(result)
    }
}


class LongestSubstring {
    fun lengthOfLongestSubstring(s: String): Int {
        val charArray: CharArray = s.toCharArray()
        var charFrequencyMap: MutableMap<Char, Int> = mutableMapOf()
        var i = 0
        var j = 0
        var longestSubstringLength = 0
        while(j < s.length) {
            charFrequencyMap.merge(charArray[j], 1, Int::plus)
            while(charFrequencyMap[charArray[j]]!! > 1) {
                charFrequencyMap.merge(charArray[i], 1, Int::minus)
                i++
            }
            val currentSubstringLength = j - i + 1
            if(currentSubstringLength > longestSubstringLength) {
                longestSubstringLength = currentSubstringLength
            }
            j++
        }
        return longestSubstringLength
    }
}
package com.random.problems.leetCode.permutationInString

class Solution1 {
    fun checkInclusion(s1: String, s2: String): Boolean {
        return checkInclusion2(s1, s2)
    }

    //  Solution 1: two loops
    //      TC: O(m) + O(n^2)
    //      SC: O(26) = O(1)
    private fun checkInclusion1(s1: String, s2: String): Boolean {
        val charMap = s1.toCharArray().toList().groupingBy { it }.eachCount()
        for ((i, c) in s2.toCharArray().withIndex()) {
            if (charMap.containsKey(c)) {
                val chars = charMap.toMutableMap()
                var j = i
                while (j < s2.length) {
                    val char = s2[j]
                    chars[char] = (chars[char] ?: break) - 1
                    if (chars[char] == 0) chars.remove(char)
                    if (chars.isEmpty()) return true
                    j++
                }
            }
        }
        return false
    }

    //  Solution 2: Sliding Window
    //      TC: O(m + n)
    //      SC: O(1)
    private fun checkInclusion2(s1: String, s2: String): Boolean {
        val charMap = s1.toCharArray().toList().groupingBy { it }.eachCount().toMutableMap()

        var i = 0
        var j = 0
        while(j < s2.length) {
            charMap[s2[j]] = (charMap[s2[j]] ?: 0) - 1
            if(j >= s1.length) {
                charMap[s2[i]] = (charMap[s2[i]] ?: 0) + 1
                i++
            }
            if(charMap.all { it.value <= 0 }) return true
            j++
        }
        return false
    }
}
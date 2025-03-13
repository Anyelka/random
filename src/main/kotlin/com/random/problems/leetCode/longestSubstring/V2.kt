package com.random.problems.leetCode.longestSubstring

class V2 {

    fun lengthOfLongestSubstring(s: String): Int {
        return lengthOfLongestSubstringSlidingWindow(s)
    }

    // Brute Force Solution:
    //          get all the possible substrings
    //          check each of them for duplicate characters
    //          keep track of the longest one without any duplicates
    //
    //      Time complexity:    O(n^3)
    //      Space complexity:   O(n)
    fun lengthOfLongestSubstringBruteForce(s: String): Int {
        if(s.isEmpty()) return 0
        var longestSubstring = s.toCharArray()[0].toString()
        for(i in s.indices) {
            for(j in i + 1.. s.length) {
                val substring = s.substring(i, j)
                if(!substring.hasAnyDuplicatesImproved() && substring.length > longestSubstring.length) {
                    longestSubstring = substring
                }
            }
        }
        return longestSubstring.length
    }

    private fun String.hasAnyDuplicates(): Boolean {
        for((i,c1) in toCharArray().withIndex()) {
            for((j,c2) in toCharArray().withIndex()) {
                if(i != j && c1 == c2) {
                    return true
                }
            }
        }
        return false
    }

    private fun String.hasAnyDuplicatesImproved(): Boolean {
        val charMap = mutableMapOf<Char, Int>()
        for(c in toCharArray()) {
            if(charMap.put(c, 1) != null) {
                return true
            }
        }
        return false
    }


    // Sliding Window Solution
    //      initialize the two pointers i = j = 0
    //      initialize a set of chars to store the current selection without duplicates
    //          iterate over the string until we reach the end and in each step
    //              add the next char (at the right pointer) if it is not in the current set
    //                  check if the current subset length is greater than max
    //              remove the first char (at the left pointer) if the next char is already in the current set
    //
    //      Time complexity:    O(n)
    //      Space complexity:
    //
    fun lengthOfLongestSubstringSlidingWindow(s: String): Int {
        val chars = mutableSetOf<Char>()
        var i = 0
        var j = 0
        var maxLength = 0
        while(j < s.length) {
            if(chars.contains(s[j])) {
                chars.remove(s[i])
                i++
            } else {
                chars.add(s[j])
                j++
                maxLength = if(maxLength > chars.size) maxLength else chars.size
            }
        }
        return maxLength
    }


}

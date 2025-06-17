package com.random.problems.leetCode.minimumWindowSubstring

class Solution1 {
    fun minWindow(s: String, t: String): String {
        return minWindow2(t, s)
    }

    // 1. Sliding window with target char frequency and updating it on each iteration
    //  TC: O(s.length)
    //  SC: O(t.length)
    private fun minWindow1(t: String, s: String): String {
        val targetChars = mutableMapOf<Char, Int>()
        for (c in t) {
            targetChars[c] = (targetChars[c] ?: 0) + 1
        }

        var minSubstring = ""

        var i = 0
        var j = -1
        while (i <= s.lastIndex && j <= s.lastIndex) {
            if (j != -1) {
                val substring = s.substring(i, j + 1)
                if (targetChars.entries.all { it.value <= 0 }) {
                    if (minSubstring == "" || substring.length < minSubstring.length) {
                        minSubstring = substring
                    }
                }
            }

            if (targetChars.entries.any { it.value > 0 }) {
                j++
                if (j > s.lastIndex) break
                if (targetChars.contains(s[j])) targetChars[s[j]] = targetChars[s[j]]!! - 1
            } else {
                if (targetChars.contains(s[i])) targetChars[s[i]] = targetChars[s[i]]!! + 1
                i++
                while (i <= s.lastIndex && targetChars[s[i]] == null) i++
            }
        }

        return minSubstring
    }

    private fun minWindow2(t: String, s: String): String {
        val remainingChars = mutableMapOf<Char, Int>()
        for (c in t) {
            remainingChars[c] = (remainingChars[c] ?: 0) + 1
        }

        var max: Int
        var minSubstring = ""

        var i = 0
        var j = -1
        while (i <= s.lastIndex && j <= s.lastIndex) {
            max = remainingChars.values.max()
            if (j != -1) {
                val substring = s.substring(i, j + 1)
                if (max == 0) {
                    if (minSubstring == "" || substring.length < minSubstring.length) {
                        minSubstring = substring
                    }
                }
            }

            if (max > 0) {
                j++
                if (j > s.lastIndex) break
                if (remainingChars.contains(s[j])) {
                    remainingChars[s[j]] = remainingChars[s[j]]!! - 1
                }
            } else {
                if (remainingChars.contains(s[i])) remainingChars[s[i]] = remainingChars[s[i]]!! + 1
                i++
                while (i <= s.lastIndex && remainingChars[s[i]] == null) i++
            }
        }

        return minSubstring
    }
}
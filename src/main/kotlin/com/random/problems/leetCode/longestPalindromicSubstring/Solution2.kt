package com.random.problems.leetCode.longestPalindromicSubstring

class Solution2 {
    fun longestPalindrome(s: String): String {
        return longestPalindromeOptimized(s)
    }

    // TC: O(n^3) ; SC: O(1)
    private fun longestPalindromeBruteForce(s: String): String {
        val chars = s.toCharArray()
        var longestPalindrome = ""
        for (i in chars.indices) {
            for (j in i..chars.size) {
                val substring = s.substring(i, j)
                if (substring.isPalindrome() && substring.length > longestPalindrome.length) {
                    longestPalindrome = substring
                }
            }
        }
        return longestPalindrome
    }

    private fun String.isPalindrome(): Boolean {
        var i = 0
        var j = length - 1
        while (i < j) {
            if (this[i] != this[j]) return false
            i++
            j--
        }
        return true
    }

    private fun longestPalindromeOptimized(s: String): String {
        var longestPalindrome = ""
        for (i in s.indices) {
            val maxLength = maxOf(palindromeLengthAround(s, i, i), palindromeLengthAround(s, i, i + 1))
            if (maxLength > longestPalindrome.length) {
                if (maxLength % 2 == 1) {
                    longestPalindrome = s.substring(i - maxLength / 2, i + 1 + maxLength / 2)
                } else {
                    longestPalindrome = s.substring(i + 1 - maxLength / 2, i + 1 + maxLength / 2)
                }
            }
        }
        return longestPalindrome
    }

    private fun palindromeLengthAround(s: String, first: Int, second: Int): Int {
        var i = first
        var j = second
        var maxLength = j - i - 1
        while (i >= 0 && j < s.length && s[i--] == s[j++]) {
            maxLength += 2
        }
        return maxLength
    }
}

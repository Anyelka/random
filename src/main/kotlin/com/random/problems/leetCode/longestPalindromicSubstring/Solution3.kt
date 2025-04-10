package com.random.problems.leetCode.longestPalindromicSubstring

class Solution3 {

    //      TC:     O(n ^ 2)
    //      SC:     O(1)
    fun longestPalindrome(s: String): String {
        val chars = s.toCharArray()
        if(chars.isEmpty()) return ""

        var longestPalindrome = ""
        var longestLength = 0

        for(i in chars.indices) {
            var start = i
            var end = i
            while(start >= 0 && end <s.length && s[start] == s[end]) {
                if(end - start + 1 > longestLength) {
                    longestPalindrome = s.substring(start, end + 1)
                    longestLength = end - start + 1
                }
                start--
                end++
            }
            start = i
            end = i + 1
            while(start >= 0 && end <s.length && s[start] == s[end]) {
                if(end - start + 1 > longestLength) {
                    longestPalindrome = s.substring(start, end + 1)
                    longestLength = end - start + 1
                }
                start--
                end++
            }
        }

        return longestPalindrome
    }
}
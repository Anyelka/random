package com.random.problems.leetCode.validPalindrome

class Solution1 {
    fun isPalindrome(s: String): Boolean {
        var l = 0
        var r = s.lastIndex

        while(l <= r) {
            while(l <= s.lastIndex && !s[l].isLetterOrDigit()) l++
            while(r >= 0 && !s[r].isLetterOrDigit()) r--
            if(l > r ) return true
            if(s[l].lowercase() != s[r].lowercase()) return false
            l++
            r--
        }
        return true
    }
}
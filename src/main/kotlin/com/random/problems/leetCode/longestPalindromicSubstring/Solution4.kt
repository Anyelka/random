package com.random.problems.leetCode.longestPalindromicSubstring

class Solution4 {
    fun longestPalindrome(s: String): String {
        // palindrome: reads the same forward and backward.
        // how to check if a string is palindrome?
        //      if we start from the middle, we can check if the next left and next right letters are the same
        // - go through each letter in s (by index)
        //   - start from index and try to expand in both directions:
        //   two types of expansions:       f.e.: babaccab
        //                                        01234567
        //                                  expand around index = 2
        //      - expand around 1 char      b -> aba -> babac X
        //                                  expand around index = 4 and index = 5
        //      - expand around 2 chars     cc -> acca -> baccab -> abaccab X
        //      if we reach end of string we cant expand

        if(s.length <= 1) return s

        fun maxSizeAround(first: Int, last: Int): Int {
            var l = first
            var r = last
            if(s[l] != s[r]) return 0
            while(l > 0 && r < s.length - 1 && s[l - 1] == s[r + 1]) {
                l--
                r++
            }
            return r - l + 1
        }

        var longestPalindromicSubstring = ""
        for(i in 0 until s.length - 1) {
            val maxSizeAroundCurrent = maxOf(maxSizeAround(i, i), maxSizeAround(i, i + 1))
            if(maxSizeAroundCurrent > longestPalindromicSubstring.length) {
                longestPalindromicSubstring = s.substring(i + 1 - (maxSizeAroundCurrent + 1) / 2, i + 1 + maxSizeAroundCurrent / 2)
            }
        }
        return longestPalindromicSubstring
    }
}
package com.random.problems.leetCode.unqiueLength3PalindromicSubsequences

class Solution1 {
    fun countPalindromicSubsequence(s: String): Int {
        val leftChars = mutableSetOf<Char>()
        val rightChars = mutableMapOf<Char, Int>()
        for(c in s) {
            rightChars[c] = rightChars.getOrDefault(c, 0) + 1
        }

        val subsequences = mutableSetOf<String>()
        for(i in s.indices) {
            rightChars.decrement(s[i])
            if(i > 0) leftChars.add(s[i - 1])
            if(i > 0 && i < s.lastIndex) {
                for(leftChar in leftChars) {
                    if(leftChar in rightChars.keys) subsequences.add("" + leftChar + s[i])
                }
            }
        }
        return subsequences.size
    }
    private fun MutableMap<Char, Int>.decrement(char: Char) {
        this[char] = this.getOrDefault(char, 1) - 1
        if(this[char] == 0) this.remove(char)
    }
}

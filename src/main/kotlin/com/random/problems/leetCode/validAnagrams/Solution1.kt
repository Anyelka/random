package com.random.problems.leetCode.validAnagrams

class Solution1 {
    fun isAnagram(s: String, t: String): Boolean {
        return isAnagram1(s,t)
    }

    // Solution 1
    //  sort both strings
    //
    //      TC:     O(n * log(n)) + O(m * log(m)) = O(max(m,n) * log(max(m,n))
    //      SC:     O(n)
    fun isAnagram1(s: String, t: String): Boolean {
        return s.toCharArray().sorted() == t.toCharArray().sorted()
    }

    // Solution 2:
    //  store chars in map
    //
    //      TC:     O(n) + O(m)
    //      SC:     O(26) = O(1)
    fun isAnagram2(s: String, t: String): Boolean {
        val chars = mutableMapOf<Char, Int>()
        for(i in s.indices) {
            chars[s[i]] = (chars[s[i]] ?: 0) + 1
        }

        for(j in t.indices) {
            chars[t[j]] = (chars[t[j]] ?: 0) - 1
            if(chars[t[j]] == 0) {
                chars.remove(t[j])
            }
        }

        return chars.isEmpty()
    }
}
package com.random.problems.leetCode.groupAnagrams

class Solution1 {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return groupAnagrams1(strs)
    }

    // 1. Solution:
    //      TC: O(n * l * log(l))
    //      SC: O(n)
    private fun groupAnagrams1(strs: Array<String>): List<MutableList<String>> {
        val anagramGroups = mutableMapOf<String, MutableList<String>>()

        for (str in strs) {
            val key = str.toCharArray().sorted().joinToString("")
            anagramGroups.getOrPut(key) { mutableListOf() }.add(str)
        }

        return anagramGroups.values.toList()
    }
}
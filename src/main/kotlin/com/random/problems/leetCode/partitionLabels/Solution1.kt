package com.random.problems.leetCode.partitionLabels

class Solution1 {
    fun partitionLabels(s: String): List<Int> {
        return partitionLabels1(s)
    }

    /*
        TC: O(n) + O(26)
        SC: O(26)

     */
    private fun partitionLabels1(s: String): MutableList<Int> {
        val charIntervalMap = mutableMapOf<Char, Pair<Int, Int>>()

        s.forEachIndexed { index, char ->
            charIntervalMap[char] = charIntervalMap[char]?.let { it.first to index } ?: (index to index)
        }

        val partSizes = mutableListOf<Int>()
        var start = 0
        var max = 0

        for (interval in charIntervalMap.values) {
            if (interval.first > max) {
                partSizes.add(max - start + 1)
                start = interval.first
            }
            max = maxOf(max, interval.second)
        }
        partSizes.add(max - start + 1)

        return partSizes
    }

}
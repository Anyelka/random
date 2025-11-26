package com.random.problems.leetCode.palindromePartitioning

class Solution1 {
    fun partition(s: String): List<List<String>> {
        /*

        how can we partition the string between i and j ?
         -> this needs to be saved so it can be used later

         */

        val partitions = Array(s.length + 1) { Array(s.length + 1) { mutableListOf<List<String>>() } }

        fun addAllNewPartitions(i: Int, j: Int, partition: String) {
            val partitionAsStringList = listOf(partition)
            partitions[i][j].add(partitionAsStringList)

            for(k in 0..i) {
                for(currentPartitions in partitions[k][i]) {
                    partitions[k][j].add(currentPartitions + partitionAsStringList)
                }
            }
        }

        fun findPalindromesAround(left: Int, right: Int) {
            var l = left
            var r = right
            var string = ""
            while(l >= 0 && r <= s.lastIndex && s[l] == s[r]) {
                if(l == r) string = "" + s[l]
                else string = s[l] + string + s[r]
                addAllNewPartitions(l,r + 1,string)
                l--
                r++
            }
        }

        for(i in s.indices) {
            findPalindromesAround(i, i)
            findPalindromesAround(i, i + 1)
        }

        return partitions[0][s.length]
    }
}
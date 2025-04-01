package com.random.problems.hackerrank.nonDivisibleSubset

class Solution1 {
    fun nonDivisibleSubset(k: Int, s: Array<Int>): Int {
        return nonDivisibleSubsetRemainders(k,s)
    }

    // 1. Naive recursive solution
    //
    //  go thorugh each number and try to create a subset starting with it
    //      in each step consider each other number:
    //      check if the subset remains correct when we include the new number.
    //      if yes, continue the search
    //
    //      Time complexity: O(n*2^n)
    //      Space complexity: O(n^2)
    //
    private fun nonDivisibleSubsetNaiveRecurison(k: Int, s: Array<Int>) =
        s.indices.maxOf { nonDivisibleSubset(k, s, mutableListOf(it)) }

    fun nonDivisibleSubset(k: Int, s: Array<Int>, subsetIndexes: List<Int>): Int {
        var max = subsetIndexes.size
        for ((i, num) in s.withIndex()) {
            if (subsetIndexes.contains(i)) continue
            if (subsetIndexes.all { (num + s[it]) % k != 0 }) {
                val nextSubsetIndexes = subsetIndexes + i
                max = maxOf(max, nonDivisibleSubset(k, s, nextSubsetIndexes))
            }
        }
        return max
    }

    // 2. Memoized recursive solution
    //
    //      same as the naive recursive solution with memoizing the result for each subset
    //      some optimizations:
    //          - keep track of the visited numbers to avoid unnecessary work
    //          - keep track of the banned numbers to avoid unnecessary work
    //      since the key of the memo is a List, we need to override equals()
    //
    //      Time complexity: O(n*2^n)
    //      Space complexity: O(n^2)
    //
    private fun nonDivisibleSubsetMemo(k: Int, s: Array<Int>): Int {
        val memo = mutableMapOf<MutableList<Int>, Int>()
        return s.indices.maxOf { nonDivisibleSubsetStartingFrom(k, s, it, memo) }
    }

    private fun nonDivisibleSubsetStartingFrom(
        k: Int,
        s: Array<Int>,
        startIndex: Int,
        memo: MutableMap<MutableList<Int>, Int>
    ): Int {
        val visitedIndexes = mutableListOf<Int>()
        val bannedIndexes = mutableListOf<Int>()
        return nonDivisibleSubsetMemo(k, s, mutableListOf(startIndex), memo, visitedIndexes, bannedIndexes)
    }

    fun nonDivisibleSubsetMemo(
        k: Int,
        s: Array<Int>,
        subsetIndexes: MutableList<Int>,
        memo: MutableMap<MutableList<Int>, Int>,
        visited: MutableList<Int>,
        bannedIndexes: MutableList<Int>
    ): Int {
        val max = subsetIndexes.size

        memo.entries.find { it.key.equals2(subsetIndexes) }?.let { return it.value }

        for ((i, num) in s.withIndex()) {
            if(visited.contains(i) || subsetIndexes.contains(i) || bannedIndexes.contains(i)) continue
            visited.add(i)
            if (subsetIndexes.any { (num + s[it]) % k == 0 }) {
                bannedIndexes.add(i)
            } else {
                val nextSubsetIndexes = subsetIndexes.toMutableList().also { it.add(i) }
                memo[subsetIndexes] = maxOf(max, nonDivisibleSubsetMemo(k, s, nextSubsetIndexes, memo, visited, bannedIndexes))
            }
        }
        return memo[subsetIndexes] ?: max
    }

    fun MutableList<Int>.equals2(other: MutableList<Int>): Boolean {
        return other.isNotEmpty() && other.all { this.contains(it) } && this.all { other.contains(it) }
    }


    // 3. Solution:
    //      The key is to realize that we only need to check the count of numbers with a specific remainder when divided
    //          by k:
    //          - for a specific k, we can include all number with either i remainder, or k-i remainder
    //              we can always take the bigger count of these two
    //          - we can always take 1 number with remainder = 0, so if we have at least 1 such number
    //              , we can add 1 to the max
    //          - if our k is even, we can also take 1 number with remainder = k/2, so if we have at least 1 such number
    //              , we can add 1 to the max
    //
    //      Time complexity: O(n+k)
    //      Space complexity: O(k)
    //
    fun nonDivisibleSubsetRemainders(k: Int, s: Array<Int>): Int {
        val remainderOccurrences = s.groupingBy { it % k }.eachCount()

        return (0..k / 2).sumOf {
            when {
                it == 0 || it.toDouble() == k / 2.0 -> if ((remainderOccurrences[it] ?: 0) > 0) 1 else 0
                else -> maxOf(remainderOccurrences[it] ?: 0, remainderOccurrences[k - it] ?: 0)
            }
        }
    }
}
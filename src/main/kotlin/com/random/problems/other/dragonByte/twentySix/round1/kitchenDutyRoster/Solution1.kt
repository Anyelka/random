package com.random.problems.other.dragonByte.twentySix.round1.kitchenDutyRoster

class Solution1 {
    fun solve(n: Int, k: Int, p: Boolean): List<String> {
        return solveK1(n,k,p)
    }

    fun solveK1(n: Int, k: Int, p: Boolean): List<String> {
        val days = mutableSetOf<Set<Int>>()

        if(k == n || k == 1) {
            days.add(IntArray(k) { it }.toSet())
        } else if(k == 2) {
            // n <= 4 if k == 2
            if(k >= n / 2) {
                days.addAll(setOf(setOf(0, 1), setOf(1, 2), setOf(2, 0)))
            } else {
                days.addAll(
                    (1..<n).map { setOf(0, it) }
                )
            }
        } else if(k > n/2) {
            days.addAll(generateAllSubsets(n, k))
        } else {
            days.addAll(generateFixedSubsets(n, k))
        }

        val count = "" + days.size
        if(!p) return listOf(count)
        return listOf(count) + days.map { it.joinToString(" ") }.toSet()
    }

    fun generateAllSubsets(n: Int, k: Int): Set<Set<Int>>  {
        val subsets = mutableSetOf<List<Int>>()

        fun generateSubsets(collected: Set<Int>, i: Int) {
            if (collected.size == k) subsets.add(collected.sorted())
            if(i >= n) return

            generateSubsets(collected + setOf(i), i + 1)
            generateSubsets(collected, i + 1)
        }

        generateSubsets(mutableSetOf(), 0)
        return subsets.map { it.toSet() }.toSet()
    }

    fun generateFixedSubsets(n: Int, k: Int): Set<Set<Int>> {
        val memo = mutableMapOf<Int, Set<Set<Int>>>()

        fun generateSubsets(collected: Set<Int>, num: Int): Set<Set<Int>> {
            if (collected.size == k) return setOf(collected)
            if(num >= n) return emptySet()
            if(memo.containsKey(num)) return memo[num]!!
            val subsets = mutableSetOf<Set<Int>>()
            for(i in num..<n) {
                val subsetsFromPlusOne = generateSubsets(collected + setOf(i), i + 1)
                if(subsetsFromPlusOne.isNotEmpty()) {
                    subsets.addAll(subsetsFromPlusOne)
                }
            }
            memo[num] = subsets
            return subsets
        }
        return generateSubsets(mutableSetOf(0), 1)
    }
}
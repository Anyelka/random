package com.random.problems.leetCode.combinationSum2

class Solution2 {
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        val combinations = mutableSetOf<List<Int>>()

        candidates.sort()

        fun getNextDifferentIndex(index: Int): Int {
            var i = index + 1
            while(i < candidates.size && candidates[i] == candidates[index]) i++
            return i
        }

        fun findCombination(subsequence: MutableList<Int>, target: Int, index: Int) {
            if(target == 0) combinations.add(subsequence.sorted())
            if(target < 0 || index == candidates.size) return
            val current = candidates[index]
            subsequence.add(current)
            findCombination(subsequence, target - current, index + 1)
            subsequence.removeLast()
            findCombination(subsequence, target, getNextDifferentIndex(index))
        }

        findCombination(mutableListOf<Int>(), target, 0)
        return combinations.toList()
    }
}
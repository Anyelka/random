package com.random.problems.leetCode.combinationSum2

class Solution1 {
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        return combinationSum23(candidates, target)
    }

    // Solution 1:
    //  DFS
    //      go through each number recursively and check the cases by
    //          - taking it or
    //          - not taking it
    //
    //      TC:     O(2 ^ n)
    //      SC:     O(2 ^ n)
    private fun combinationSum22(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        combinationSum22(candidates, target, 0, emptyList(), result)
        return result.toList()
    }


    private fun combinationSum22(
        candidates: IntArray,
        target: Int,
        index: Int,
        taken: List<Int>,
        result: MutableSet<List<Int>>
    ) {
        if (target == 0) result.add(taken.sorted())
        if (target < 0 || index >= candidates.size) return
        val value = candidates[index]
        combinationSum22(candidates, target - value, index + 1, taken + value, result)
        combinationSum22(candidates, target, index + 1, taken, result)
    }

    //  Solution 3:
    //      sort and skip numbers with same value
    private fun combinationSum23(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        combinationSum23(candidates.sorted().toIntArray(), target, 0, emptyList(), result)
        return result.toList()
    }


    private fun combinationSum23(
        candidates: IntArray,
        target: Int,
        index: Int,
        taken: List<Int>,
        result: MutableSet<List<Int>>
    ) {
        if (target == 0) result.add(taken.sorted())
        if (target < 0 || index >= candidates.size) return
        val value = candidates[index]
        val nextTaken = taken + value
        combinationSum23(candidates, target - value, index + 1, nextTaken, result)
        var indexAfterSkip = index + 1
        while(indexAfterSkip < candidates.size && candidates[indexAfterSkip] == candidates[indexAfterSkip - 1]) indexAfterSkip++
        combinationSum23(candidates, target, indexAfterSkip, taken, result)
    }
}
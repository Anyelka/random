package com.random.problems.hackerrank.nonDivisibleSubset

class Solution2 {
    //      TC:     O(n) + O(k / 2) = O(n)
    //      SC:     O(k)
    fun nonDivisibleSubset(k: Int, s: Array<Int>): Int {
        val occurrenceMap = s.map { it % k }.groupingBy { it }.eachCount()

        return (0..k / 2).sumOf {
            if(it == 0 || (k % 2 == 0 && it == k / 2)) {
                if(occurrenceMap.containsKey(it)) 1 else 0
            } else {
                maxOf(occurrenceMap[it] ?: 0, occurrenceMap[k - it] ?: 0)
            }
        }
    }

}